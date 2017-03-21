package com.dragon.study.springboot.tutorial.importpom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by dragon on 2017/3/21.
 */
@Data
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
