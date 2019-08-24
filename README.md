**jacq-common** contains JPA / JAX-RS classes / interfaces only.

**jacq-service** contains the actual implementation using manager classes.

The JACQ project is documented under https://development.senegate.at/confluence/display/JACQ.

## Dependencies

- [Java SE 8](https://docs.oracle.com/javase/8/) (JDK)
- [Maven](https://maven.apache.org) (> 3.3.9)

Although Maven is used for dependency management, the **JACQ Input** needs [Geckodriver](https://github.com/mozilla/geckodriver) installed for tests.

## Development Setup

:information_source: The required development setup is also available as a docker implementation in the [JACQ Docker](https://github.com/jacq-system/jacq-docker) project.

**Required:**

- [WildFly] 10.1.0.Final
- [Biodiversity](https://rubygems.org/gems/biodiversity/)
  A global name parser used to split scientific names into components. The ruby implementation has to be installed and started manually using the following commands:
  ```sh
  gem install biodiversity
  parserver -r -o json
  ```

**Recommended:**

- Netbeans (> 8.0.2)
  - WildFly Plugin / JavaEE Edition

Maven projects can be opened directly from Netbeans.

## Deployment

The [Maven WildFly Plugin](https://docs.jboss.org/wildfly/plugins/maven/latest/index.html) is included in the parent module (jacq) and can be used to deploy packages to a [WildFly] application server.

```sh
mvn wildfly:deploy-only -f jacq -pl ../module-1,../module-2
```

deploy-only is used to avoid dependency errors on goals executed before deployment.

[WildFly]: https://rubygems.org/gems/biodiversity/
