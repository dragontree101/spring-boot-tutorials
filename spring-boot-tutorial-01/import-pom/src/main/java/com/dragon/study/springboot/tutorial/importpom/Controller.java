package com.dragon.study.springboot.tutorial.importpom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dragon on 2017/3/19.
 */
@RestController
@RequestMapping("/tutorials-01")
public class Controller {

  @RequestMapping("/hello")
  public String helloWorld() {
    String str = "Hello World";
    System.out.println(str);
    return str;
  }
}
