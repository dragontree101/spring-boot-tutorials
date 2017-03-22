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
public class ParentModel {
  @Expose
  private List<ChildModel> children;
  private String strField;
  @Expose
  @SerializedName("map_children")
  private Map<String, ChildModel> mapChildren;
}
