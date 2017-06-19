import java.util.concurrent.*

def queue = new ArrayBlockingQueue(100)
Receiver.singleton = Receiver.of(queue)
DatagramProcessor.singleton = DatagramProcessor.of(queue)
Receiver.start()
DatagramProcessor.start()
Transmitter.start()

Thread.start {
  for (int i=0; i<100; i++) {
     def message = "Transmit $i"
     Transmitter.singleton.broadcast(message)
     sleep(500)
  }
}
