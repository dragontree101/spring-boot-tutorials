package com.dragon.study.springboot.tutorial.importpom.bean;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

/**
 * Created by dragon on 2017/3/21.
 */
@Data
@ToString
public class TutorialBean {
  private String key1;
  private String key2;
  private String key3;
  private List<String> listKey;
  private Map<String, String> mapKey;
  private TutorialQiNiuBean tutorialQiNiuBean;
}
