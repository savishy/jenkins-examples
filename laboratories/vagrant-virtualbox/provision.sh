#!/bin/bash
ansible-galaxy install -r requirements.yml -p provisioning/
ansible-playbook -i provisioning/inventories/virtualbox provisioning/playbook.yml
