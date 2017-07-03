import java.net.*

class Census implements IDatagramProcessor {

    def infos = [:]
    static singleton

    static of() {
        new Census()
    }

    def process(DatagramPacket packet) {
        def info = DeviceInfo.from(packet)
        infos[info.address] = info
        println "Census = $infos"
    }

    String toString() {
        infos.toString()
    }
}
