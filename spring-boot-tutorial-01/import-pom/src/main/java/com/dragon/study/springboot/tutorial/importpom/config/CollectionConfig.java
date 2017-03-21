package com.dragon.study.springboot.tutorial.importpom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by dragon on 2017/3/21.
 */
@Data
@Validated
@ConfigurationProperties
public class CollectionConfig {

  @NotNull
  private List<String> list;

  @NotNull
  private Map<String, String> map;

}
