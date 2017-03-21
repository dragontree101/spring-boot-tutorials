package com.dragon.study.springboot.tutorial.importpom.bean;

import com.dragon.study.springboot.tutorial.importpom.config.CollectionConfig;
import com.dragon.study.springboot.tutorial.importpom.config.QiNiuConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dragon on 2017/3/21.
 */
@Configuration
@EnableConfigurationProperties({
    CollectionConfig.class, QiNiuConfig.class
})
public class TutorialBeanConfiguration {

  @Autowired
  private QiNiuConfig qiNiuConfig;

  @Autowired
  private CollectionConfig collectionConfig;

  @Value("${key1}")
  private String key1;

  @Value("${key2}")
  private String key2;

  @Value("${key3:default_value3}")
  private String key3;

  @Bean
  @ConditionalOnMissingBean
  public TutorialQiNiuBean tutorialQiNiuBean() {
    TutorialQiNiuBean tutorialQiNiuBean = new TutorialQiNiuBean();
    tutorialQiNiuBean.setAccessKey(qiNiuConfig.getAccessKey());
    tutorialQiNiuBean.setSecretKey(qiNiuConfig.getSecretKey());
    tutorialQiNiuBean.setLocalImageFilePath(qiNiuConfig.getLocalImageFilePath());
    return tutorialQiNiuBean;
  }

  @Bean
  @ConditionalOnMissingBean
  public TutorialBean tutorialBean(TutorialQiNiuBean tutorialQiNiuBean) {
    TutorialBean tutorialBean = new TutorialBean();
    tutorialBean.setKey1(key1);
    tutorialBean.setKey2(key2);
    tutorialBean.setKey3(key3);

    tutorialBean.setListKey(collectionConfig.getList());
    tutorialBean.setMapKey(collectionConfig.getMap());
    tutorialBean.setTutorialQiNiuBean(tutorialQiNiuBean);
    return tutorialBean;
  }
}
