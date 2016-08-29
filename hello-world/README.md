This is a very basic "hello world" demonstration of using Pipeline. 

## How to run

1. First start your Jenkins instance (e.g. type http://localhost:8080 in your browser.)
  Make sure you have setup Jenkins completely, as described in the [getting-started docs.](https://jenkins.io/download/)
1. Now create a new job. Name it `test-pipeline`. The job type should be "Pipeline".
1. Configure your Jenkins job like this:
![alt tag](https://raw.githubusercontent.com/savishy/jenkins-examples/master/img/helloworld.png)
1. Hit Save.
2. Run the job.

The important thing to note is that we specified two things in the job configuration
1. the path to an SCM repository that contains Groovy Jenkins Pipeline scripts; and 
2. the path to our `Jenkinsfile` that contains the actual job config.
    In this example we use the `hello-world/Jenkinsfile` file. In future examples you would simply edit the config to point to a `Jenkinsfile` in a different folder.
