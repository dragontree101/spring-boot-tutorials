package com.dragon.study.springboot.tutorial.param.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dragon on 2017/3/22.
 */
@Configuration
public class GsonConfiguration {

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
}
