package com.dragon.study.springboot.tutorial.parentpom;

import com.dragon.study.springboot.tutorial.parentpom.bean.TutorialBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
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
    assertThat(entity.getBody()).isEqualTo("Hello World");
    assertThat(entity.getStatusCodeValue()).isEqualTo(200);
  }

  @Test
  public void testTutorialBean() {
    ResponseEntity<TutorialBean> entity = this.restTemplate.getForEntity("/tutorials-01/tutorialBean", TutorialBean.class);
    assertThat(entity.getStatusCodeValue()).isEqualTo(200);

    TutorialBean tutorialBean = entity.getBody();
    assertThat(tutorialBean.getList().size()).isEqualTo(3);
    assertThat(tutorialBean.getMap().size()).isEqualTo(2);
  }
}
