class CensusQuery extends Command {

  final Census census

  CensusQuery(census) {
      super('census',true)
      this.census = census
  }

  static of(census) {
      new CensusQuery(census)
  }

  String perform(arg) {
      census.toString()
  }

  static main(args) {
      of().broadcastNow('')
  }
}
