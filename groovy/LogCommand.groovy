class LogCommand extends Command {

  LogCommand() {
      super('',false)
  }

  static of() {
      new LogCommand()
  }

  String perform(arg) {
      println arg
  }

  static main(args) {
      of().broadcastNow(args[0])
  }
}
