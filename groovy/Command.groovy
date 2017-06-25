abstract class Command implements IDatagramProcessor {

  final String prefix

  Command(prefix) {
      this.prefix = prefix
  }

  def process(DatagramPacket packet) {
      def string = string(packet)
      if (matches(string)) {
          perform(arg(string))
      }
  }

  def matches(string) {
      string.startsWith(prefix)
  }

  def arg(string) {
      printable(afterPrefix(nullTerminated(string)))
  }

  def afterPrefix(string) {
      string.split(prefix + " ")[1]
  }

  def nullTerminated(string) {
      string.split("\0")[0]
  }

  def printable(string) {
      string.replaceAll("\\p{C}", "?")
  }

  abstract perform(arg)

  def string(packet) {
      new String(packet.data)
  }

  def broadcastNow(message) {
      Transmitter.of().broadcastNow("$prefix $message")
  }

}
