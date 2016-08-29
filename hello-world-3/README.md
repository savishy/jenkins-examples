This example demonstrates script security.

## Script Security

Loading scripts from a Jenkinsfile means the script executes in a "sandbox"
with limited permissions.

To be able to grant the script permissions you need the
[Script Security Plugin](https://wiki.jenkins-ci.org/display/JENKINS/Script+Security+Plugin). This
plugin is usually preinstalled.

## Instructions


### Run hello-world-3 example

This Jenkinsfile is exactly the same as that in `hello-world-2.`

Once you have run `hello-world-3` you will encounter a failed job, due to
script permissions.

## Approve scripts to use classes

Now, do the following:

1. From the home page of Jenkins, click "Manage Jenkins" in the side bar.
1. Scroll down until you see "In-Process Script Approvals". Click it.

Now you should see the following screen, with a permission waiting to be
approved.

![before approval](https://github.com/savishy/jenkins-examples/raw/master/img/helloworld5.png)

Click "Approve", and now the class or classes are added to a whitelist. And
scripts are allowed to access these classes.

![after approval](https://github.com/savishy/jenkins-examples/raw/master/img/helloworld6.png)

**Note:** This is not the only way to ensure script security; there are other,
more detailed ways to prevent scripts from doing something nasty. Hit the
plugin page to know more.

## Run hello-world-3 again

Now you can rerun hello-world-3. This time it will pass.

![success](https://github.com/savishy/jenkins-examples/raw/master/img/helloworld7.png)


## References
1. http://stackoverflow.com/a/29547362/682912
1. https://wiki.jenkins-ci.org/display/JENKINS/Script+Security+Plugin
