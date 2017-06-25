import groovy.transform.*
import java.time.*

@Canonical class DeviceInfo {

   DeviceAddress address
   Instant       lastHeard

   static DeviceInfo from(DatagramPacket packet) {
       new DeviceInfo(new DeviceAddress(packet.address,packet.port),Instant.now())
   }
}
