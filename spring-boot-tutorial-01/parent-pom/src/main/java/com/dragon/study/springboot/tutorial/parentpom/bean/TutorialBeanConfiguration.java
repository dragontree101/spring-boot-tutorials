package com.dragon.study.springboot.tutorial.parentpom.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Created by dragon on 2017/3/21.
 */
@Configuration
public class TutorialBeanConfiguration {
  @Value("#{'${list}'.split(',')}")
  private List<String> list;

  @Value("#{${map}}")
  private Map<String,String> map;

  @Bean
  @ConditionalOnMissingBean
  public TutorialBean tutorialBean() {
    TutorialBean tutorialBean = new TutorialBean();
    tutorialBean.setList(list);
    tutorialBean.setMap(map);
    return tutorialBean;
  }
}
