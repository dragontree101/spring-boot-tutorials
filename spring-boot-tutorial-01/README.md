# 前言
本篇文章主要介绍spring-boot两种不同的`pom.xml`的写法来构建一个简单的基于SpringMVC的restful形式的微服务。

# 使用技术
spring-boot  1.5.2.RELEASE
springmvc


# 项目结构
spring-boot的官方推荐了两种pom.xml的写法
(1) 利用parent的方式来构建
```
  <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.2.RELEASE</version>
    </parent>
```
通过这样的方式表示，表示使用spring-boot提供的parent来统一管理具体依赖jar包的版本信息，这样就可以不需要写具体依赖项目的jar包版本了，统一使用spring-boot指定的版本

(2) 利用import的方式来构建
```
  <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>1.5.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```
通过这种方式，表示引入了 `spring-boot-dependencies` 来管理来统一管理具体依赖jar包的版本信息。这样就可以不需要写具体依赖项目的jar包版本了，统一使用spring-boot指定的版本。

(3) 引入核心依赖
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```
引入了上面的依赖之后，再加上一个简单的 `Controller` 就完成了服务端的搭建。这个依赖就是web服务的核心依赖（基于springmvc构成的restful服务接口）

# 启动项目
由于使用了spring-boot的插件
```
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <classifier>exec</classifier>
                </configuration>
            </plugin>
```
所以会生成一个exec.jar，这个jar包里面包含了所有需要运行的依赖，然后我们可以通过 `java -jar import-pom-1.0.0-SNAPSHOT-exec.jar` 来启动服务，等待服务启动以后，可以执行 `curl '127.0.0.1:8080/tutorials-01/hello'` 来请求服务的接口。这样就算利用spring-boot完成了第一个入门级别的微服务。

# 两种方式的对比以及分析
对于上面两种引入spring-boot的方式，倾向于使用第二种 `import` 的方式，因为 `parent` 模式有几种弊端:

1. 任何项目只能有一个 `parent`，如果使用了spring-boot的 `parent`，这样项目就没办法使用其他的 `parent`，但是一般在一个大型项目中，使用本项目的 `parent` 是很常见的模式，所以spring-boot的 `parent` 并不适合。

2. 项目通常不仅仅使用spring-boot的开源组件，所以可能还会存在使用其他开源组件，也需要使用其他项目的默认jar包，这个通过 `parent`的方式是无法完成的， 但是 `import` 的方式可以多个并存，同时通过多个 `import` 来一起管理项目所使用的jar包。

