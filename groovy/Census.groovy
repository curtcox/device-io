import java.net.*

class Census implements IDatagramProcessor {

    static singleton

    static of() {
        new Census()
    }

    def process(DatagramPacket packet) {
        def received = new String(packet.getData())
        println "Received $received ${packet.address} ${packet.port}"
    }
}
