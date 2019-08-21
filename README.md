**jacq-common** contains JPA / JAX-RS classes / interfaces only.

**jacq-service** contains the actual implementation using manager classes.

## Dependencies

- [Java SE 8](https://docs.oracle.com/javase/8/) (JDK)
- [Maven](https://maven.apache.org) (> 3.3.9)

Although Maven is used for dependency management, the **JACQ Input** needs [Geckodriver](https://github.com/mozilla/geckodriver) installed for tests.

## Deployment

The [Maven WildFly Plugin](https://docs.jboss.org/wildfly/plugins/maven/latest/index.html) is included in the parent module (jacq) and can be used to deploy packages to a local [WildFly](https://www.wildfly.org/) application server.

:information_source: The [JACQ Docker](https://github.com/jacq-system/jacq-docker) project is recommended for this purpose.

```sh
mvn wildfly:deploy-only -f jacq -pl ../module-1,../module-2
```

deploy-only is used to avoid dependency errors on goals executed before deployment.

## Development Setup

- Netbeans (> 8.0.2)
  - WildFly Plugin / JavaEE Edition
- WildFly 10.1.0.Final

Maven projects can be opened directly from Netbeans.
