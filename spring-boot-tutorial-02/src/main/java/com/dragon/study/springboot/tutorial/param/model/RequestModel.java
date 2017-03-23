package com.dragon.study.springboot.tutorial.param.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by dragon on 2017/3/23.
 */
@Data
public class RequestModel {
  @NotNull
  private String param1;
  private String param2;
}
