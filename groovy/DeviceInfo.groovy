import groovy.transform.*
import java.time.*

@Canonical class DeviceInfo {

   DeviceAddress address
   Instant       lastHeard

   static DeviceInfo from(DatagramPacket packet) {
       new DeviceInfo(DeviceAddress.from(packet),Instant.now())
   }
}
