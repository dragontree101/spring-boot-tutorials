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


-----------------------------------------------
-----------------------------------------------


# 前言

任何一个项目不可能少得了配置文件， 所以配置文件在spring-boot中有很多呈现方式，本篇主要介绍一下最常用的配置文件的方式，代码依附于第一篇开发的简单Hello World的微服务，并且项目代码也是和第一篇的代码在同一个项目之中。

# 常规配置

1. 最常用的 `key-value` 形式的配置就是通过 `@Value` 注解

假设配置文件 application.yaml里面的内容为
```
key1: value1
key2: ${value2:default_value2}
```

然后代码中使用了
```
  @Value("${key1}")
  private String key1;

  @Value("${key2}")
  private String key2;

  @Value("${key3:default_value3}")
  private String key3;
```

对于配置文件里面的`key1`表示，对应的值是`value1`, 如果使用`@Value("${key1}")` 的时候没有找到对应的值程序就会抛出异常退出。

对于配置文件里面的`key2`表示，对应的值是`default_value2`, 但是可以支持通过手动设置java启动参数`-Dvalue2`来设置`value2`来改变默认值。

对于 `@Value("${key3:default_value3}")` 表示试图去找配置文件中的 `key3`, 如果找到了就用配置文件中的值，没有找到的话，不会抛出异常，而是用 `default_value3` 替代。

2. 把配置文件转成对象
首先在配置类上面使用注解
```
@Configuration
@EnableConfigurationProperties({
    CollectionConfig.class, QiNiuConfig.class
})
```
其中注解 `@Configuration` 表示这个类为配置类，需要读配置文件，`@EnableConfigurationProperties` 表示需要可以把配置文件映射成 `CollectionConfig` 和 `QiNiuConfig` 类的对象，是符合java的面向对象的思想的。

(1) 对象类型: 下面是`QiNiuConfig`类
```
@Validated
@ConfigurationProperties(prefix = "qiniuParam")
public class QiNiuConfig {
  @NotNull
  private String accessKey;
  @NotNull
  private String secretKey;
  private String localImageFilePath;
  private int otherParam1;
  @NotNull
  private int otherParam2;
//  @NotNull
  private Integer otherParam3;
}
```
对应的配置文件
```
qiniuParam:
  accessKey: testAccessKey
  secretKey: testSecretKey
  localImageFilePath: /data/localUpdateFile/
  otherParam1: 11111
```
在spring-boot的1.5.x版本里面，加入了对于配置对象的字段进行了验证的功能，我们在类上面加上`@Validated` 注解， 然后在字段上面增加`@NotNull` 表示该字段不能为空，也可以增加其他注解， 进行各种参数规则的校验，然后`@ConfigurationProperties` 注解表示前缀统一，可以看到我们配置文件中的对象都是属于 `qiniuParam` 域之下的，所以我们加了`prefix=qiniuParam`，另外还有一点要注意，`@NotNull` 注解，在java的原生类型的时候是不会生效的，可以看到配置文件中 `otherParam2` 并没有赋值，但是启动不会出错，他会默认把原生类型 `int` 赋值成0， 如果把 `int` 改成了 `Integer`，启动就会出错。

(2) 集合类型
配置文件
```
list:
  - list_value1
  - list_value2
  - list_value3

map:
  map_key1: map_value1
  map_key2: map_value2
```
集合类型不能用`@Value`进行注入，必须要通过类对象进行构造
```
@Validated
@ConfigurationProperties
public class CollectionConfig {
  @NotNull
  private List<String> list;
  @NotNull
  private Map<String, String> map;
}
```
同样可以增加参数验证注解来配合进行验证。并且注意，配置文件只支持`list`类型和`map`类型的配置参数。

(3) 其他
如果一定要利用`@Value`来接受`list`参数，可以通过利用 `@Value`注解的表达式函数进行解析
```
@Value("#{'${list}'.split(',')}")
private List<String> list;

@Value("#{${map}}")  
private Map<String,String> map;
```
然后配置文件中写成
```
list: topic1,topic2,topic3
map: "{key1: 'value1', key2: 'value2'}"
```
ps: 注意上面的map解析中，一定要用""把map所对应的value包起来，要不然解析会失败，导致不能转成 `Map<String,String> map;` 对象。
