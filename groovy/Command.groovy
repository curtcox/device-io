import java.util.concurrent.*

abstract class Command implements IDatagramProcessor {

  final String prefix
  final boolean result
  final Transmitter transmitter

  Command(prefix,result,transmitter) {
      this.prefix      = prefix
      this.result      = result
      this.transmitter = transmitter
  }

  Command(prefix,result) {
      this(prefix,result,Transmitter.singleton)
  }

  def process(DatagramPacket packet) {
      def message = message(packet)
      try {
          processMessage(packet,message)
      } catch (e) {
          throw new RuntimeException("Processing $prefix $message",e)
      }
  }

  def message(packet) {
      nullTerminated(string(packet))
  }

  def processMessage(packet,message) {
      if (matches(message)) {
          def response = perform(arg(message))
          if (result) {
              reply(packet,response)
          }
      }
  }

  def reply(packet,response) {
      transmitter.reply(packet,response)
  }

  def matches(string) {
      string.startsWith(prefix)
  }

  def arg(string) {
      printable(afterPrefix(string)).trim()
  }

  def afterPrefix(string) {
      string.split(prefix)[1]
  }

  def nullTerminated(string) {
      string.split("\0")[0]
  }

  def printable(string) {
      string.replaceAll("\\p{C}", "?")
  }

  abstract String perform(arg)

  def string(packet) {
      new String(packet.data)
  }

  def listenForResponses() {
      println 'listening for responses'
      def queue    = new SynchronousQueue()
      Receiver.singleton = Receiver.of(queue)
      Receiver.start()
      Thread.start() {
          println 'started'
          for (;;) {
              def packet = queue.take()
              def string = nullTerminated(string(packet))
              println string
          }
      }
  }

  def broadcastNow(message) {
      if (result) {
          listenForResponses()
      }
      def transmitter = Transmitter.of()
      transmitter.broadcastNow("$prefix $message")
  }

}
