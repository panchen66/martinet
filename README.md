**MARTINET**


**Committed to real-time log flow processing!**

MARTINET is a C/S framework for statistical computation of real-time log streams. 

1.Reliable asynchronous processing

2.Convenient management based on HTTP

3.Near Real Time handle

4.Zero intrusion code(future)

5.Dynamic Processing and Custom Processing(future)

6.support multi-tenancy and Distributed(future)

7.Adapt to other logging frameworks(future)

**Getting Started**

**client**

1. clone client project , mvn install it
2. add it to your project's pom
```
<dependency>
	<groupId>com.panchen</groupId>
	<artifactId>martinet-client</artifactId>
	<version>1.0.0</version>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
3.modify your log4j2.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="info"
	packages="com.panchen.martinet.client.log">

	<Appenders>
		<Log2Martinet name="log2Martinet">
			<PatternLayout
				pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] [%-5p] {%F:%L} - %m%n" />
		</Log2Martinet>
	</Appenders>
	<Loggers>

		<Root level="debug">
			<AppenderRef ref="log2Martinet" />
		</Root>
	</Loggers>
</Configuration>
```
4. add in the startup class
```
@EnableMartinetClient
public class Client {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Client.class, args);
    }
}

```
**server**

1. clone server project ,mvn install it
2. add it to springboot demo project's pom
```
<dependency>
	<groupId>com.panchen</groupId>
	<artifactId>martinet-server</artifactId>
</dependency>
```
3. add in the startup class
```
@EnableMartinetServer
public class Server {

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}
}
```

**manage server**

You can access this path through HTTP to see the number of logs processed：
```
localhost:8080/count
```
Currently supported class names and log levels for querying the number of logs based on multi-dimensionality
```
http://localhost:8080/dimension/LOGLEVEL(CLASSNAME)
```

