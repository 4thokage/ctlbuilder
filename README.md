# ctlbuilder
Java utility for generating oracle CTL files (standalone)

### How To Use?
See the main method in CTLBuilder.java:59 and check existing test classes for basic examples
```java
public static String generateCTL(DBTable dbTable, Collection<DBColumn> columns, CTLTypesEnum ctlType,
			CTLOptions opts) {}
```
#### Using in Your Maven Project 
1. Add the following repository to your pom:
```xml
    <repository>
        <id>repository-jsr</id>
        <url>https://repo.jose-rodrigues.info/repository/snapshots/</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
```


2. Add clbuilder as a dependency:
```xml
<dependency>
    <groupId>pt.zenit.oracle.ctl</groupId>
    <artifactId>ctlbuilder</artifactId>
    <version>0.0.1-RELEASE</version>
</dependency>
```
