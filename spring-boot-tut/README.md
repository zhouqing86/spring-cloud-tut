# Spring Boot Tutorial

### XML, JSON Restful API.

JSON support:  @RestController 或者 @ResponseBody

XML support: @XmlRootElement注解在实体类上，注意次实体类必须有一个无参数的构造方法。


访问获取json: [http://localhost:8080/json.json](http://localhost:8080/json.json) 或者 `curl -H "Accept:Application/xml" http://localhost:8080/json`


访问获取xml: [http://localhost:8080/json.xml](http://localhost:8080/json.xml) 或者 `curl -H "Accept:Application/json" http://localhost:8080/json`

测试发现，url中的后缀优先级高于headers中的优先级。

### Introduce jpa and H2 database

注意，如果要使得[http://localhost:8080/h2-console](http://localhost:8080/h2-console) 能够被访问，需要引入`compile('com.h2database:h2')`。并且在application.properties中配置`spring.h2.console.enabled=true`.

