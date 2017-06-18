import java.net.*

class Transmitter {

  static start() {
      def socket = new DatagramSocket()
      for (int i =0; i<1000; i++) {
          def message = "Message $i"
          def buf     = message.getBytes()
          def packet  = new DatagramPacket(buf, buf.length,Config.group, 4446)
          println "Transmit $message ${socket.localPort}"
          socket.send(packet)
          sleep(250)
      }
  }

}
