# AWS Laboratory

This sub-directory provides a "pop up" laboratory with servers for use with the Jenkins examples. The laboratory is provisioned using Vagrant + AWS and can be used for providing an environment to try out code examples with Jenkins.

## Features ##

1. *A good use-case of infra-as-code*. Allows a functionally complete lab to be setup from scratch, on demand, and torn down after use.
1. *Runs on AWS.* As long as you have an AWS account you can make this work.

A server environment consisting of the following is provisioned:

### Jenkins Server

This includes:
* Jenkins v2.32.x
* Most of the basic plugins, e.g
** git
** docker
** pipeline
** etc.

**Jenkins is a Docker Container**

Jenkins runs as a Docker container, based off [savishy/docker-jenkins](https://hub.docker.com/r/savishy/docker-jenkins/).

For more details such as the exact plugins, the port etc. look at [https://github.com/savishy/docker-examples/tree/master/dockerized-applications/docker-jenkins](https://github.com/savishy/docker-examples/tree/master/dockerized-applications/docker-jenkins).

### Nexus Server

The Nexus server also runs as a Docker container, based on the latest Nexus image.
