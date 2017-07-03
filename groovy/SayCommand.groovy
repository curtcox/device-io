class SayCommand extends Command {

  SayCommand() {
      super('say',false)
  }

  static of() {
      new SayCommand()
  }

  String perform(arg) {
      "say $arg".execute()
  }

  static main(args) {
      of().broadcastNow(args[0])
  }
}
