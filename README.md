The JACQ project documentation can be found at https://development.senegate.at/confluence/display/JACQ.
This document contains instructions on dependencies, usage and contribution of and to this project.

The Java project is separated into 8 mostly interdependent modules. The most important are listed here:

- **jacq-common** contains JPA / JAX-RS classes / interfaces only.
- **jacq-service** contains the actual implementation using manager classes.
- **jacq-names** implements the [Common Names Web service](https://development.senegate.at/confluence/display/JACQ/Common+Names+Webservice) (CNS).

## Dependencies

- [Java SE 8](https://docs.oracle.com/javase/8/) (JDK)
- [Maven](https://maven.apache.org) (> 3.3.9)

While all modules use Maven for their dependency management, the **jacq-input** module needs [Geckodriver](https://github.com/mozilla/geckodriver) installed manually for tests.

## Development Setup

:information_source: The required development setup is also available as a Docker implementation in the [JACQ Docker](https://github.com/jacq-system/jacq-docker) project.

**Required:**

- [WildFly] 10.1.0.Final
- [Biodiversity](https://rubygems.org/gems/biodiversity/)
  A global name parser used to split scientific names into components. The ruby implementation has to be installed and started manually using the following commands:
  ```sh
  gem install biodiversity
  parserver -r -o json
  ```

**Recommended:**

- NetBeans (> 8.0.2)
  - WildFly Plugin / JavaEE Edition

Maven projects can be opened directly from NetBeans.

## Deployment

The [Maven WildFly Plugin](https://docs.jboss.org/wildfly/plugins/maven/latest/index.html) is included in the parent module (jacq) and can be used to deploy packages to a [WildFly] application server.

```sh
mvn wildfly:deploy-only -f jacq -pl ../module-1,../module-2
```

deploy-only is used to avoid dependency errors on goals executed before deployment.

[WildFly]: https://rubygems.org/gems/biodiversity/

As the JACQ project is using a microservice architecture, the frontend-code needs to know where the respective endpoints can be reached. This is defined through a system property inside the JVM.

Add the following parameters to your JVM startup:

```
-Djacq.serviceUrl=http://localhost:8080/jacq-service/rest/
-Djacq.serviceNamesUrl=http://localhost:8080/jacq-names/rest/
-Djacq.serviceOutputUrl=http://localhost:8080/jacq-service-output/rest/
-Djacq.serviceReportUrl=http://localhost:8080/jacq-service-report/rest/
-Djacq.gnparserUrl=localhost
```

The URLs should be adapted depending on your setup.
