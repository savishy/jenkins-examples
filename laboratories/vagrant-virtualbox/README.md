## Ansible + Virtualbox provisioner

## Prerequisites

### Linux-based Ansible System

* Linux system with Ansible 2.0+
* I use a Linux VM (`docker-host`) available [here](http://github.com/savishy/vagrant-boxes).

### Virtualbox VM with SSH enabled

You need to have a Virtualbox VM that can act as a provisioning target. One example is [vagrant-ssh](http://github.com/savishy/vagrant-boxes/).

The target box should already be running. Once it is, modify the [inventories/virtualbox/hosts](inventories/virtualbox/hosts) file with the box IP address.

## How to Run

Execute [./provision.sh](provision.sh).
