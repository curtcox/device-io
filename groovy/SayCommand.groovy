class SayCommand extends Command {

  SayCommand() {
      super('say')
  }

  static of() {
      new SayCommand()
  }

  def perform(arg) {
      "say $arg".execute()
  }

  static main(args) {
      of().broadcastNow(args[0])
  }
}
