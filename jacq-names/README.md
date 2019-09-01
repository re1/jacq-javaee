# JACQ Common Names Webservice

The JACQ Common Names Webservice is documented on [Confluence](https://development.senegate.at/confluence/display/JACQ/Common+Names+Webservice).

## Deployment

Build `jacq-names` and also `jacq-service` and `jacq-common` which `jacq-names` depends on. Then deploy `jacq-service` and `jacq-names` modules using the [Maven WildFly Plugin](https://docs.jboss.org/wildfly/plugins/maven/latest/index.html).

```sh
mvn package -f jacq -pl ../jacq-common,../jacq-service,../jacq-names
mvn wildfly:deploy-only -f jacq -pl ../jacq-service,../jacq-names
```

## Usage

The Common Names Webservice can be reached from https://127.0.0.1:8080/jacq-names/rest/commonNames. Both requests and results are based on the [OpenRefine Reconciliation API](https://github.com/OpenRefine/OpenRefine/wiki/Reconciliation-Service-API).

As most queries will be JSON objects the URL has to be [encoded](https://en.wikipedia.org/wiki/Percent-encoding) correctly. Valid URLs include:

- **Raw query**:
  http://localhost:8080/jacq-names/rest/commonNames?query=%7B%22type%22%3A%22%2Fname%2Fcommon%22%2C%20%22query%22%3A%22Veronica+caninotesticulata%22%7D.
- **OpenRefine Single Query Mode** ([Deprecated](https://github.com/OpenRefine/OpenRefine/wiki/Reconciliation-Service-API#deprecated-single-query-mode)):
  http://localhost:8080/jacq-names/rest/commonNames?query=%7B%22query%22%3A%22Veronica+caninotesticulata%22%7D
- **OpenRefine Multiple Query Mode**:
  http://localhost:8080/jacq-names/rest/commonNames?queries=%7B%22q1%22%3A%7B%22query%22%3A%22Veronica+caninotesticulata%22%7D%2C%22q2%22%3A%7B%22query%22%3A%22Wolffia+arrhiza%22%7D%7D

- **XML Format**:
  localhost:8080/jacq-names/rest/commonNames?query=%7B%22type%22%3A%22%2Fname%2Fcommon%22%2C%22query%22%3A%22Veronica%20caninotesticulata%22%7D&format=edmSkos
