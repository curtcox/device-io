import java.util.concurrent.*

def queue = new ArrayBlockingQueue(100)
Transmitter.singleton       = Transmitter.of()
Receiver.singleton          = Receiver.of(queue)
Census.singleton            = Census.of()
def census                  = Census.singleton
def processors              =
 [
     census,
     LogCommand.of(), PrintCommand.of(), SayCommand.of(),
     CensusQuery.of(census)
 ]
DatagramProcessor.singleton = DatagramProcessor.of(queue,processors)
Heartbeat.singleton         = Heartbeat.of(Transmitter.singleton)

Receiver.start()
DatagramProcessor.start()
Transmitter.start()
Heartbeat.start()
