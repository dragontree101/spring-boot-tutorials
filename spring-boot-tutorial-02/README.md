# 前言
现在restful是流行的通信方式，利用restful+json的形式来完成后端服务的请求，这篇文章主要简单介绍一下常用的输入请求，并且以json的方式返回数据。

# 请求
1. 常用的请求方式有GET、POST、PUT三种方式。
2. 通过 `@RequestMapping`注解可以通过path参数声明请求的路径， method参数来声明请求的方式，consumes参数来声明request的类型，produces参数来声明response的类型。
3. 通过 `@RequestParam`注解来表明GET、POST、PUT请求的参数。
4. 通过 `@RequestHeader` 注解来表明请求头参数。
5. 通过 `@PathParam` 注解来表明restful形式的path路径参数。
6. 通过 `@ModelAttribute` 来把传入的参数转换成对象的形式。

# 利用gson构造json数据返回
1. spring-boot默认使用jackson方式来构造json数据，但是我们想要使用gson，就首先需要在pom.xml加入gson依赖的同时，排除jackson依赖。
2. 在Application类通过 `@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class })` 来排除掉jackson的序列化。
3. 创建一个`HttpMessageConverters`来利用gson进行object到json的转换
```
@Bean
  public HttpMessageConverters gsonConverters() {
    Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

    final Gson gson = new GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
//        .setExclusionStrategies(new GsonExclusionStrategy())
//        .serializeNulls()
        .create();

    GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
    gsonHttpMessageConverter.setGson(gson);

    messageConverters.add(gsonHttpMessageConverter);
    return new HttpMessageConverters(true, messageConverters);
  }
```
其中可以设置gson的行为和属性
4. 然后在对象里面通过gson提供的注解完成需要完成的事情

# 传入参数的验证
可以参考之前写的一篇文章，关于利用《使用validator-api来验证spring-boot的参数》的文章：   http://www.jianshu.com/p/2c2da2adef81
