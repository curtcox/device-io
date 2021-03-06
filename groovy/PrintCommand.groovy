class PrintCommand extends Command {

  PrintCommand() {
      super('print',false)
  }

  static of() {
      new PrintCommand()
  }

  String perform(arg) {
      println arg
  }

  static main(args) {
      of().broadcastNow(args[0])
  }
}
