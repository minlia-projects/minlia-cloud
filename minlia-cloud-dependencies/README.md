添加License

mvn com.mycila:license-maven-plugin:format

移除License
mvn com.mycila:license-maven-plugin:remove


Add jasypt for properties encryption

java -cp ~/.m2/repository/org/jasypt/jasypt/1.9.2/jasypt-1.9.2.jar  org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="contactspassword" password=supersecretz algorithm=PBEWithMD5AndDES

----ENVIRONMENT-----------------

Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 24.45-b08



----ARGUMENTS-------------------

algorithm: PBEWithMD5AndDES
input: contactspassword
password: supersecretz



----OUTPUT----------------------

XcBjfjDDjxeyFBoaEPhG14wEzc6Ja+Xx+hNPrJyQT88=

```
      <dependency>
        <groupId>com.github.ulisesbocchio</groupId>
        <artifactId>jasypt-spring-boot-starter</artifactId>
        <version>${jasypt-spring-boot-starter.version}</version>
      </dependency>
```