package com.apress.prospring5.ch3.annotated;

import com.apress.prospring5.ch3.provider.MessageProvider;
import org.springframework.stereotype.Component;

@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider {

  @Override
  public String getMessage() {
    return "Hello World!";
  }
}
