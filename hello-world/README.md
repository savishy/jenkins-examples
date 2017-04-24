This is a very basic "hello world" demonstration of using Pipeline. 

## How to run

### Configure a Jenkins job

1. First start your Jenkins instance (e.g. type http://localhost:8080 in your browser.)
  Make sure you have setup Jenkins completely, as described in the [getting-started docs.](https://jenkins.io/download/)
1. Now create a new job. Name it `test-pipeline`. The job type should be "Pipeline".
1. Configure your Jenkins job like this:
![job config](https://raw.githubusercontent.com/savishy/jenkins-examples/master/img/helloworld.png)
1. Hit Save.
2. Run the job.

### Notes

The important thing to note is that we specified two things in the job configuration
1. the path to an SCM repository that contains Groovy Jenkins Pipeline scripts; and 
2. the path to our `Jenkinsfile` that contains the actual job config.
    In this example we use the [`hello-world/Jenkinsfile`](Jenkinsfile) file. In future examples you would simply edit the config to point to a `Jenkinsfile` in a different folder.

Now lets observe something when the job runs:
![console output](https://github.com/savishy/jenkins-examples/raw/master/img/helloworld3.png)

This above is the console output. It contains the exact output of the job, along with some additional information about the pipeline itself.

![no stages](https://github.com/savishy/jenkins-examples/raw/master/img/helloworld2.png)

The message in screenshot recommends adding stages to further break down our pipeline. Let's do that in example [`hello-world-2`](../hello-world-2/README.md).
