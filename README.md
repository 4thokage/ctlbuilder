# ctlbuilder
Java utility for generating oracle CTL files (standalone)

### How To Use?
See the main method in CTLBuilder.java:59 and check existing test classes for basic examples
```java
public static String generateCTL(DBTable dbTable, Collection<DBColumn> columns, CTLTypesEnum ctlType,
			CTLOptions opts) {}
```
#### Using in Your Maven Project 

1. Add ctlbuilder as a dependency:
```xml
<dependency>
    <groupId>pt.zenit.oracle.ctl</groupId>
    <artifactId>ctlbuilder</artifactId>
    <version>0.0.2-RELEASE</version>
</dependency>
```
