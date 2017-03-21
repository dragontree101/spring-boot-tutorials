package com.dragon.study.springboot.tutorial.parentpom.bean;

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
  private List<String> list;
  private Map<String, String> map;
}
