#!/bin/bash
set -e
echo "$1"
host=`/usr/bin/vagrant awsinfo -m $1 -k host`
echo "host: $host"
