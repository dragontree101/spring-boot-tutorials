package com.dragon.study.springboot.tutorial.importpom;

import com.dragon.study.springboot.tutorial.importpom.bean.TutorialBean;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dragon on 2017/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext
public class HelloWorldTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testHelloWorld() {
    ResponseEntity entity = this.restTemplate.getForEntity("/tutorials-01/hello", String.class);
    Assert.assertEquals("Hello World", entity.getBody());
    Assert.assertEquals(200, entity.getStatusCodeValue());
  }

  @Test
  public void testTutorialBean() {
    ResponseEntity<TutorialBean> entity = this.restTemplate.getForEntity("/tutorials-01/tutorialBean", TutorialBean.class);
    Assert.assertEquals(200, entity.getStatusCodeValue());

    TutorialBean tutorialBean = entity.getBody();
    Assert.assertEquals("value1", tutorialBean.getKey1());
    Assert.assertEquals("default_value2", tutorialBean.getKey2());
    Assert.assertEquals("default_value3", tutorialBean.getKey3());
    Assert.assertEquals(3, tutorialBean.getListKey().size());
    Assert.assertEquals(2, tutorialBean.getMapKey().size());
    Assert.assertEquals("testAccessKey", tutorialBean.getTutorialQiNiuBean().getAccessKey());
    Assert.assertEquals("testSecretKey", tutorialBean.getTutorialQiNiuBean().getSecretKey());
    Assert.assertEquals("/data/localUpdateFile/", tutorialBean.getTutorialQiNiuBean().getLocalImageFilePath());
  }

}
