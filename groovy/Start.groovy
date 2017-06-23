import java.util.concurrent.*

def queue = new ArrayBlockingQueue(100)
Transmitter.singleton       = Transmitter.of()
Receiver.singleton          = Receiver.of(queue)
DatagramProcessor.singleton = DatagramProcessor.of(queue)
Heartbeat.singleton         = Heartbeat.of(Transmitter.singleton)

Receiver.start()
DatagramProcessor.start()
Transmitter.start()
Heartbeat.start()
