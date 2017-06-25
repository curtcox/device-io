class PrintCommand extends Command {

  PrintCommand() {
      super('print')
  }

  static of() {
      new PrintCommand()
  }

  def perform(arg) {
      println arg
  }

  static main(args) {
      of().broadcastNow(args[0])
  }
}
