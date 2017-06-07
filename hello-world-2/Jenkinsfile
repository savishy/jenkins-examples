#!/groovy

node('master') {

  stage 'print some basic info'
  def currentDir = pwd()
  echo "-- pwd: $currentDir"
  echo "-- workspace: ${env.WORKSPACE}"
  sh "ls"

  ws {
  common1 = load "${currentDir}@script/scripts/common.groovy"
  echo "-- this is a test"
  println common1.getRandom(3)
  }

  stage 'load groovy script'
  echo "-- lets load a groovy script so that we can use its methods."
  echo "-- this is a great approach to minimize job-specific configuration and maximize code reuse."
  common = load "${currentDir}@script/scripts/common.groovy"

  stage 'call function from groovy script'
  echo "-- lets call the getRandom() function from common.groovy"
  println common.getRandom(3)

  stage 'say hello'
  echo "-- this is a stage. A stage is a logical separation of your build pipeline."
  echo "-- stages can be used to separate out distinct but connected components of your pipeline."

  stage 'say your name'
  echo "I am node ${env.NODE_NAME}"

}
