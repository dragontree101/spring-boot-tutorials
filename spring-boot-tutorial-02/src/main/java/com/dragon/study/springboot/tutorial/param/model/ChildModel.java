package com.dragon.study.springboot.tutorial.param.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

/**
 * Created by dragon on 2017/3/22.
 */
@Data
@ToString
public class ChildModel {
  @Expose
  @SerializedName("string_field")
  private String stringField;
  @Expose
  @SerializedName("list_field")
  private List<String> listField;
  private Map<String, String> mapField;
  @Expose
  @SerializedName("str_null")
  private String strNull;
}
