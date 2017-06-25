import java.time.*

class Heartbeat {

  Transmitter transmitter

  static singleton

  Heartbeat(transmitter) {
      this.transmitter = transmitter
  }

  static of(transmitter) {
      new Heartbeat(transmitter)
  }

  def loop() {
      for (;;) {
          broadcast(message())
          sleep(10 * 1000)
      }
  }

  def broadcast(message) {
      transmitter.broadcast(message)
  }

  def message() {
      "Heartbeat ${Instant.now()}"
  }

  static def start() {
      Thread.start {
          singleton.loop()
      }
  }

}
