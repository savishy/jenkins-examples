# jenkins-examples
This is a repository of simple Jenkins examples.

Most examples at present use the Jenkins pipeline feature introduced around Jenkins 1.6, so keep the following docs handy:

1. [basic doc](https://jenkins.io/doc/pipeline/)
2. [command reference](https://jenkins.io/doc/pipeline/steps/)

## Prerequisites ##

You should have a Jenkins instance to try this out on. The easiest way is to [download it for your operating system](https://jenkins.io/download/) but you can also [run a Jenkins Docker container.](https://hub.docker.com/_/jenkins/).

## Using the examples

Each `hello-world-*` folder contains a `Jenkinsfile` which demonstrates a basic Pipeline concept. To try out each example, read the `README` file located in each folder.

*What order should you follow?*

1. [hello-world](https://github.com/savishy/jenkins-examples/tree/master/hello-world)
1. [hello-world-2](https://github.com/savishy/jenkins-examples/tree/master/hello-world-2)
1. [hello-world-3](https://github.com/savishy/jenkins-examples/tree/master/hello-world-3)

And other `hello-world-*` examples sequentially.

## Troubleshooting ##

### `java.lang.NullPointerException: Cannot invoke method on null object`

You might encounter an error similar to the following when executing a
pipeline:

```
    java.lang.NullPointerException: Cannot invoke method getRandom() on null object
    at org.codehaus.groovy.runtime.NullObject.invokeMethod(NullObject.java:91)
        at org.codehaus.groovy.runtime.callsite.PogoMetaClassSite.call(PogoMetaClassSite.java:48)
            at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)
```

One situation where this error is encountered, is when

* you load a groovy script from your `Jenkinsfile` but
* that script has not been terminated with `return this`.
