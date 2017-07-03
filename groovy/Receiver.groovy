import java.net.*
import java.util.function.*

class Receiver {

  Queue           packets
  MulticastSocket socket
  InetAddress     group

  static singleton

  Receiver(packets,socket,group) {
      this.packets = packets
      this.socket  = socket
      this.group   = group
  }

  static of(Queue packets) {
      new Receiver(packets,new MulticastSocket(Config.port),Config.group)
  }

  def loop() {
      for (;;) {
          receive()
      }
  }

  def receive() {
      def buf    = new byte[256]
      def packet = new DatagramPacket(buf, buf.length)
      socket.receive(packet)
      process(packet)
  }

  def process(packet) {
      packets.put(packet)
  }

  def joinGroup() {
      socket.joinGroup(group)
  }

  static start() {
      Thread.start {
          singleton.joinGroup()
          singleton.loop()
      }
  }

}
