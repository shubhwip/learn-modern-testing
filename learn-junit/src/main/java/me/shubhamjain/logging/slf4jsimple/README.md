## Library Used
https://github.com/s4u/slf4j-mock

## Run
- Make sure to comment `slf4j` bindings except `slf4j-mock` in pom.xml
- Clean project `mvn clean install`
- Run Tests


### Add Dependencies
```shell
<dependencies>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4jVersion}</version>
        </dependency>
        <dependency>
            <groupId>org.simplify4u</groupId>
            <artifactId>slf4j2-mock</artifactId>
            <version><!-- check relases page --></version>  
            <scope>test</scope>        
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.28</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.4.0</version>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>5.4.0</version>
        </dependency>
<dependencies>
```

### Important Note
Please remember, that you can only have one SLF4J binding or provider on classpath, in the most case you must replace org.slf4j:slf4j-simple by org.simplify4u:slf4j-mock.

