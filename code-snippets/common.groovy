#!/groovy

/**
REFERENCES
http://stackoverflow.com/a/36958537


**/

/**

Run a Docker SSH Container.

By default port 2200 will be forwarded to port 22 on the container.
You can specify additional ports using the parameters.

PARAMETERS:
- ports: ports to publish for the container, e.g "-p P1:P1 -p P2:P2"

RETURNS:
- A container object that can be used in further calls.
**/
def runDockerSSHContainer(ports = '') {
  echo "-- Starting Docker Container"
  def name = "docker-ssh-container"
  def parentImage = "savishy/docker-ssh:1.1"
  def localMountDir = "/opt/install"
  def remoteMountDir = "/opt/install"

  def containerID = this.getDockerContainerID(parentImage)
  if (containerID != null) {
    echo "-- deleting existing container $containerID!"
    this.removeContainer(containerID)
  }
  def image = docker.image(parentImage)
  def container = image.run("--name $name -d -v $localMountDir:$remoteMountDir -p 2200:22 $ports")

  echo "-- container started with id: $container.id"
  this.getDockerContainerIP(container.id)
  return container
}

/**
Retrieve container IP of a docker container running off of a
certain Docker image.

PARAMETERS:
- parentImage: e.g 'savishy/docker-ssh:1.1' This is the exact name of a Docker image
 which your container is based on.
**/
def getDockerContainerID(parentImage) {
  def output = sh (script: "/usr/bin/docker ps -aq -f ancestor=$parentImage",
    returnStdOut: true)
  if (output != null)
    return output
    // return output.split("\r?\n")
  else
    return null;
}

/**
Stop and remove a specific container by ID.
**/
def removeContainer(containerID) {
  sh (script: "/usr/bin/docker rm -f $containerID")
}

/**
Given a docker container get its IP address.
**/
def getDockerContainerIP(containerID) {
  def cmd = 'docker inspect --format ' +
    '\'{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}\' ' +
    containerID
  echo "-- docker command: $cmd"
  def output = sh(script: cmd, returnStdOut: true)
  return output
}

def stopContainer(containerObject) {
  containerObject.stop()
}

return this;
