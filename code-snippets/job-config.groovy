/**

Job Configuration Methods go here.

REFERENCES:
- https://www.cloudbees.com/blog/top-10-best-practices-jenkins-pipeline-plugin
**/

/**
This method checks out an Accurev Repository based on the
parameters you provide.
*NOTE*: The server UUID might need changing if you reconfigure
Accurev Server in Jenkins.

**PARAMETERS**:

depot: the accurev depot name
stream: the accurev stream name

**/
def checkoutAccurevRepository(depot,stream) {
  echo "-- Checkout stream $depot/$stream to $pwd"
  def exitCode = checkout([  $class: 'AccurevSCM',
                  depot: "$depot",
                  directoryOffset: '',
                  filterForPollSCM: '',
                  reftree: '',
                  serverUUID: '720af072-38eb-42a9-a1cf-328a8f2fede0',
                  snapshotNameFormat: '',
                  stream: "$stream",
                  subPath: '', workspace: '', wspaceORreftree: 'none'])
  if (exitCode == 1) {
    error "Step checkoutAccurevRepository() errored out. See the logs above for errors. Failing the build."
  }
}

/**

Checkout a Gitlab Git repository.

**PARAMETERS**:

gitlabProject: the gitlab project
gitlabRepo: gitlab repository
gitBranch: git branch name
gitlabUrl: Optional Parameter. Default value of 'git@longscm01p.emea.kuoni.int

e.g

git url: git@longscm01p.emea.kuoni.int:cm/scripts.git
is equivalent to:
git url: [gitlabUrl]:[gitlabProject]/[gitlabRepo].git

ASSUMPTIONS:

The method assumes there is a Jenkins credential called 'jenkins-gitlab-ssh-key'
stored in the Credentials Page of Jenkins.
This credential needs to be able to access all git repositories.


**/

def checkoutGitlabRepository(
  gitlabProject,
  gitlabRepo,
  gitBranch,
  gitlabUrl = 'git@longscm01p.emea.kuoni.int') {
  def exitCode = git url: "$gitlabUrl:$gitlabProject/${gitlabRepo}.git",
                      branch: gitBranch,
                      credentialsId: 'jenkins-gitlab-ssh-key'

  if (exitCode == 1) {
    error "Step checkoutGitlabRepository() errored out. See the logs above for errors. Failing the build."
  }
}

/**
Build a Gradle Project

PARAMETERS
gradleToolName: The exact name of the Gradle Tool as configured
in Jenkins > Manage Jenkins > Global Tool Configuration
This param allows you to use multiple gradle versions.

javaToolName: The exact name of Java as configured in Global Tool Configuration.

gradleTask: The tasks to run with gradle e.g "clean build"

buildPath: the path where build.gradle is located.

extraArgs: any extra arguments, such as build properties can be passed here.
**/

def buildGradleProject(gradleToolName,javaToolName,gradleTask,buildPath,extraArgs='') {
  //find Gradle Installation
  def gradleTool = tool name: "$gradleToolName", type: 'gradle'
  //find Java Installation
  def jt = tool name: "$javaToolName", type: 'jdk'
  echo "-- JDK: $jt, Gradle: $gradleTool"

  //prepend Gradle Home, Java Home to Path.
  withEnv([
    "PATH+GRADLE=${gradleTool}/bin",
    "PATH+JAVA=${jt}/bin",
    ]) {
      sh "cd $buildPath; gradle -g ${env.WORKSPACE} $gradleTask $extraArgs"
  }
}

/**
**/
def provisionUltraESBTarget(type) {
  stage "provision $type docker container"
  build job: 'docker-container',
      parameters: [string(name: 'CONTAINER_TYPE', value: type),
      booleanParam(name: 'ANSIBLE_VERBOSE', value: false),
      string(name: 'SSH_PORT', value: '2205')]
}

/**
* Deploy UltraESB application with Ansible.
**/
def deployUltraESB(inventoryDir,playbookPath) {
  stage "Deploy UltraESB: $inventoryDir"
  def extraVars = "--extra-vars \"artifact_path=${env.WORKSPACE}\""
  step ([$class: 'CopyArtifact',
    projectName: 'docker-container',
    filter: 'hosts']);

  sh "cat hosts"
  sh "cp hosts $inventoryDir/hosts"
  runAnsiblePlaybook("$inventoryDir","$playbookPath", "$extraVars")
}


/**
Run Ansible Playbook to perform an Ansible Task.

PARAMETERS
- inventory: inventory directory containing the
  hosts file, as well as directories host_vars and group_vars

- playbook: path to a playbook file.
**/
def runAnsiblePlaybook(inventory,playbook,extraVars='') {
  ansiblePlaybook credentialsId: 'jenkins-gitlab-ssh-key',
    extras: extraVars,
    installation: 'Ansible-2.3.0',
    inventory: inventory,
    playbook: playbook,
    sudo: true, sudoUser: "root"
}

/**
A Stub method that will allow reading the maven version from a
POM File.

This is useful when you want to extract maven POM details
(e.g groupId, artifactId, version of app) to use later.

Requires Pipeline Utility Steps Jenkins plugin.
https://stackoverflow.com/a/37639425/682912

**/
def readMavenPOM() {
}


/**
When within a Gradle workspace, retrieve the application version and group
info from the build.gradle file.

TODO method needs testing

**/

def getGradleProjectVersion(gradleToolName,buildPath) {

  //find Gradle Installation
  def gradleTool = tool name: "$gradleToolName", type: 'gradle'
  echo "-- Gradle: $gradleTool"

  //prepend Gradle Home, Java Home to Path.
  withEnv([
    "PATH+GRADLE=${gradleTool}/bin",
  ]) {
    def projectVersion = sh "cd $buildPath; gradle properties | grep -i version", returnStdout: true
    return projectVersion
  }
}

/**
TODO method needs testing

**/
def getGradleProjectGroup() {
  def projectGroup= sh script: "gradle getGroup()", returnStdout: true
  return projectGroup
}

return this;
