import java.net.*

class DatagramProcessor implements IDatagramProcessor {

  Queue  packets
  String group
  int    port
  List   processors

  static singleton

  DatagramProcessor(packets,processors) {
      this.packets    = packets
      this.processors = processors
  }

  static of(packets,processors) {
      new DatagramProcessor(packets,processors)
  }

  def loop() {
      for (;;) {
          process(packets.take())
      }
  }

  def process(DatagramPacket packet) {
      for (processor in processors) {
          processor.process(packet)
      }
  }

  static start() {
      Thread.start {
          singleton.loop()
      }
  }
}
