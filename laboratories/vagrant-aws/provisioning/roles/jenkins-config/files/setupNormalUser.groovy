for (i=0; i<10; i++) {
  jenkins.model.Jenkins.instance.securityRealm.createAccount("user$i", "password123")
}
