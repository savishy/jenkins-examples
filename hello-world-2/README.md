# hello-world-2


This example shows you the following concepts

1. Printing basic info and referencing environment variables.
1. Using the `stage` functionality.
1. Loading commonly used functionality from a script.

If you haven't run the basic [`hello-world`](../hello-world/README.md) example, *please take a look at that example first.*

## How to use

If you have already run the `hello-world` example, all you need to do is edit the groovy
script path for the job configuration as shown below:

![edit script path](https://github.com/savishy/jenkins-examples/raw/master/img/helloworld4.png)

## Notes: loading groovy scripts from Jenkinsfile

When a *regular i.e freestyle* job is run, the workspace folder is simply a
unique folder represented by the `$WORKSPACE` variable.

In pipeline jobs, however, the workspace is a more fluid concept, i.e
`$WORKSPACE` will no longer be a reliable basis to determine where you are.

This means, if you want to have your `Jenkinsfile` load another file, you need
to understand the folder structure a bit more clearly.

Try the following:

1. Go to your Jenkins Home directory. On my Linux machine, it's
   `/var/lib/jenkins`.
1. Open the `workspace` folder. Look at the folder names.
1. *If your pipeline job is called `test-pipeline`* you should see a folder
   named:

  `test-pipeline@script`
1. Look inside the contents of this folder, and you will see our repository
   (`jenkins-examples`) checked out.

**What did we learn?**

To locate and load a script from within a `Jenkinsfile` you will need to load
the path with the following prefix

```
<JENKINS_HOME>/workspace/<JOB_NAME>@script/
```

In the
[`Jenkinsfile`](https://github.com/savishy/jenkins-examples/blob/master/hello-world-2/Jenkinsfile)
for the `hello-world-2` example, we do this as

```
def currentDir = pwd()
common = load "${currentDir}@script/scripts/common.groovy"
```

## Run the job: fails!

When you run the job, you might see that it failed with error 

```
java.io.FileNotFoundException: /var/lib/jenkins/workspace/petclinic-pipeline@script/scripts/common.groovy (No such file or directory)
	at java.io.FileInputStream.open0(Native Method)
	at java.io.FileInputStream.open(FileInputStream.java:195)
```

The possible reason is issue #5. If you enabled 'sparse or lightweight checkout' in your job configuration, the above directory will not be allocated and the job will fail. 

## Run the job: fails!

When you run this job, you might notice that it failed.

Look at the console output and you will see

```
org.jenkinsci.plugins.scriptsecurity.sandbox.RejectedAccessException: Scripts not permitted to use new java.util.Random
    at org.jenkinsci.plugins.scriptsecurity.sandbox.whitelists.StaticWhitelist.rejectNew(StaticWhitelist.java:185)
        at org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.SandboxInterceptor.onNewInstance(SandboxInterceptor.java:130)
            at org.kohsuke.groovy.sandbox.impl.Checker$3.call(Checker.java:191)
```

This is intentional and teaches you a concept called "Script Security". Let's
move on to [hello-world-3](../hello-world-3/README.md) to learn more.

## References

1. [Tutorial from Jenkins](https://github.com/jenkinsci/pipeline-plugin/blob/master/TUTORIAL.md)
