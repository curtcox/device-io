import java.net.*
import java.util.concurrent.*

class Transmitter {

  Queue          packets
  DatagramSocket socket
  InetAddress    group
  int            port

  static singleton

  Transmitter(packets,socket,group,port) {
      this.packets = packets
      this.socket  = socket
      this.group   = group
      this.port    = port
  }

  static of() {
      new Transmitter(new SynchronousQueue(),new DatagramSocket(),Config.group,Config.port)
  }

  def loop() {
      for (;;) {
          transmit(packets.take())
      }
  }

  def broadcast(message) {
      packets.put(packet(message))
  }

  def broadcastNow(message) {
      socket.send(packet(message))
  }

  def transmit(DatagramPacket packet) {
      socket.send(packet)
  }

  def packet(message) {
      def buf = message.getBytes()
      new DatagramPacket(buf, buf.length,group,port)
  }

  static start() {
      Thread.start {
          singleton.loop()
      }
  }

}
