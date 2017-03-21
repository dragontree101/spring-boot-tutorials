package com.dragon.study.springboot.tutorial.importpom.bean;

import lombok.Data;
import lombok.ToString;

/**
 * Created by dragon on 2017/3/21.
 */
@Data
@ToString
public class TutorialQiNiuBean {
  private String accessKey;
  private String secretKey;
  private String localImageFilePath;
}
