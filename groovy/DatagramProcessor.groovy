class DatagramProcessor {

  Queue          packets
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
      println "Received $received ${packet.address} ${packet.port}"
  }

  static start() {
      Thread.start {
          singleton.loop()
      }
  }
}
