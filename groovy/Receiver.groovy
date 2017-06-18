import java.net.*

class Receiver {

  static start() {
    def socket = new MulticastSocket(Config.port)
    socket.joinGroup(Config.group)
    for (;;) {
        def buf    = new byte[256]
        def packet = new DatagramPacket(buf, buf.length)
        socket.receive(packet)
        def received = new String(packet.getData())
        println "Received $received ${socket.localPort} ${packet.address} ${packet.port}"
    }

  }

}
