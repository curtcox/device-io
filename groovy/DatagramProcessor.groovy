class DatagramProcessor {

  Queue          packets
  DatagramSocket socket
  String         group
  int            port

  static singleton

  DatagramProcessor(packets) {
      this.packets = packets
  }

  static of(packets) {
      new DatagramProcessor(packets)
  }

  def loop() {
      for (;;) {
          process(packets.take())
      }
  }

  def process(packet) {
      def received = new String(packet.getData())
      println "Received $received ${socket.localPort} ${packet.address} ${packet.port}"
  }

  static start() {
      Thread.start {
          singleton.loop()
      }
  }
}
