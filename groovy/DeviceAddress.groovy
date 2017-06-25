import groovy.transform.*

@Canonical class DeviceAddress {
   InetAddress address
   int         port
}
