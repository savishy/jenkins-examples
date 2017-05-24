# Laboratory

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

### Nexus Server

The Nexus server also runs as a Docker container, based on the latest Nexus image.

## Notes/Tips/Gotchas

### Make sure to `git submodule update` frequently

The directory [laboratories/vagrant-aws/provisioning/ansible-roles](laboratories/vagrant-aws/provisioning/ansible-roles) is a git submodule that pulls [savishy/ansible-roles](http://github.com/savishy/ansible-roles).

*To ensure you have the latest version of code, make sure you do the following once you clone the repository:*

```
git submodule update --force --init --remote
```

(reference.)[https://stackoverflow.com/a/40718820/682912]
