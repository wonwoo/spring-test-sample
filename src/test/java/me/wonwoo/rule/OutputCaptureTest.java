package me.wonwoo.rule;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by wonwoolee on 2017. 4. 19..
 */
public class OutputCaptureTest {

  @Rule
  public OutputCapture capture = new OutputCapture();

  @Test
  public void printOutPutTest() {
    System.out.println("hello wonwoo!");
    assertThat(capture.toString(), containsString("wonwoo!"));
    assertThat(capture.toString(), equalTo("hello wonwoo!\n"));
  }
}
