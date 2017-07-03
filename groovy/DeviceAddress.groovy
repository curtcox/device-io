import groovy.transform.*

@Canonical class DeviceAddress {
   InetAddress address
   int         port

   static DeviceAddress from(DatagramPacket packet) {
      new DeviceAddress(packet.address,packet.port)
   }
}
