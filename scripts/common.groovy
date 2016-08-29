#!/groovy

def getRandom() {
  return new java.util.Random().nextInt(10 * num)
}
