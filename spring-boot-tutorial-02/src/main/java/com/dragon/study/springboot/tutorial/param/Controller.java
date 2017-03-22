package com.dragon.study.springboot.tutorial.param;


import com.dragon.study.springboot.tutorial.param.model.ChildModel;
import com.dragon.study.springboot.tutorial.param.model.ParentModel;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dragon on 2017/3/19.
 */
@RestController
@RequestMapping("/tutorials-02")
public class Controller {

  @RequestMapping(path = "/hello",
      method = RequestMethod.GET,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String helloWorld() {
    String str = "Hello World";
    System.out.println(str);
    return str;
  }


  @RequestMapping(path = "/getRequestParam",
      method = RequestMethod.GET,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String getRequest(
      @RequestParam(name = "param1")
      String param1,
      @RequestParam(name = "param2", required = false, defaultValue = "value2")
      String param2,
      @RequestHeader(name = "header")
      String header
  ) {
    System.out.println("param1 is " + param1 + ", param2 is " + param2 + ", header is " + header);
    return "get request param";
  }

  @RequestMapping(path = "/getRequestObject",
      method = RequestMethod.GET,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public List<ParentModel> getRequestObject() {
    List<ParentModel> list = new ArrayList<>();
    list.add(createParentModel(1));
    list.add(createParentModel(2));
    list.add(createParentModel(3));
    return list;
  }


  private ParentModel createParentModel(int i) {
    ParentModel parentModel = new ParentModel();
    parentModel.setStrField("parent" + i);

    List<ChildModel> list = new ArrayList<>();
    list.add(createChildModel(i * 10 + 1));
    list.add(createChildModel(i * 10 + 2));
    list.add(createChildModel(i * 10 + 3));

    parentModel.setChildren(list);

    Map<String, ChildModel> map = new HashMap<>();
    map.put(String.valueOf(i * 100 + 1), createChildModel(i * 100 + 1));
    map.put(String.valueOf(i * 100 + 2), createChildModel(i * 100 + 2));
    parentModel.setMapChildren(map);
    return parentModel;
  }

  private ChildModel createChildModel(int i) {
    ChildModel childModel = new ChildModel();
    childModel.setStringField("child" + i);
    childModel.setListField(Lists.newArrayList(String.valueOf(i), String.valueOf(i*i)));
    childModel.setMapField(Maps.newHashMap(String.valueOf(i), String.valueOf(i * i)));
    return childModel;
  }

}
