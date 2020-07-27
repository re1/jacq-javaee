# JACQ Common Names Webservice

The JACQ Common Names Webservice is documented on [Confluence](https://development.senegate.at/confluence/display/JACQ/Common+Names+Webservice).

## Deployment

Clean the project if necessary. Build `jacq-names` and also `jacq-service` and `jacq-common` which `jacq-names` depends on. Then deploy `jacq-service` and `jacq-names` modules using the [Maven WildFly Plugin](https://docs.jboss.org/wildfly/plugins/maven/latest/index.html).

```sh
mvn clean
mvn package -f jacq -pl org.jacq:jacq-common,org.jacq:jacq-service,org.jacq:jacq-names
mvn wildfly:deploy-only -f jacq -pl org.jacq:jacq-service,org.jacq:jacq-names
```

## Usage

The Common Names Webservice can be reached from https://localhost:8080/jacq-names/rest/commonNames. Both requests and results are based on the [OpenRefine Reconciliation API](https://github.com/OpenRefine/OpenRefine/wiki/Reconciliation-Service-API).

As most queries will be JSON objects the URL has to be [encoded](https://en.wikipedia.org/wiki/Percent-encoding) correctly. Valid URLs include:

- **Raw query**:
  http://localhost:8080/jacq-names/rest/commonNames?query=%7B%22type%22%3A%22%2Fname%2Fcommon%22%2C%20%22query%22%3A%22Veronica+caninotesticulata%22%7D
- **OpenRefine Single Query Mode** ([Deprecated](https://github.com/OpenRefine/OpenRefine/wiki/Reconciliation-Service-API#deprecated-single-query-mode)):
  http://localhost:8080/jacq-names/rest/commonNames?query=%7B%22query%22%3A%22Veronica+caninotesticulata%22%7D
- **OpenRefine Multiple Query Mode**:
  http://localhost:8080/jacq-names/rest/commonNames?queries=%7B%22q1%22%3A%7B%22query%22%3A%22Veronica+caninotesticulata%22%7D%2C%22q2%22%3A%7B%22query%22%3A%22Wolffia+arrhiza%22%7D%7D
- **XML Format**:
  http://localhost:8080/jacq-names/rest/commonNames?query=%7B%22type%22%3A%22%2Fname%2Fcommon%22%2C%22query%22%3A%22Veronica%20caninotesticulata%22%7D&format=edmSkos

## Inclusion of data sources

A class implementing the `CommonNamesSource` interface has to be added to the `org.jacq.service.names.sources` package.

### ReST Web services

ReST Web service [Sources](./src/main/java/org/jacq/service/names/sources) providing ReST endpoints can be queried using
the [RESTEasy](https://resteasy.github.io/) Proxy Framework. 
In that case a Web service interface has to be added to the `org.jacq.service.names.sources.services` package.

#### Web service interface

`org.jacq.service.names.sources.services.SourceService`

```java
@Path("/")
public interface SourceService {
    @GET
    @Path("/")
    String query(@QueryParam("query") String query);
}
```

#### `CommonNamesSource` implementation

`org.jacq.service.names.sources.Source`

```java
@ManagedBean
public class Source implements CommonNamesSource {
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        SourceService service = SourcesUtil.getProxy(
            SourceService.class,
            "https://www.source.tld/webservice"
        );
    }
}
```

#### Caching

ReST Web services can easily be cached by extending the `CachedWebServiceSource` class.

```java
@ManagedBean
public class Source extends CachedWebServiceSource {

    private static final String serviceUrl = "https://www.source.tld/webservice";

    @PostConstruct
    public void init() {
        setServiceId(1);        // serviceId used to identify the cached source
        setTimeout(2592000);    // timeout in seconds (here 30 days)
    }

    /**
     * @see CommonNamesSource#query(NameParserResponse)
     */
    @Override
    public ArrayList<CommonName> query(NameParserResponse query) {
        ArrayList<CommonName> results = new ArrayList<>();
        // get cached response if possible
        String response = getResponse(query);
        if (response == null || response.isEmpty()) return results;
        
        // process response and add common names to the results list

        return results;
    }
}
```

Cached Web service sources implement `CommonNamesSource` through `CachedWebServiceSource` and therefore replace the 
original `CommonNamesSource` implementation.

### Add a source to the `CommonNameManager`

The source implementation then needs to be injected into the `CommonNameManager` class and added as a source to query.

**Inject a source into the `CommonNameManager`**:

```java
@Inject
protected Source source;
```

**Add source to query tasks**:

```java
commonNamesSources.add(source);
```

It is recommended to add the injected source as a parameter to the 
`List<CommonNamesSource> commonNamesSources = Arrays.asList();` call to avoid multiple method calls.