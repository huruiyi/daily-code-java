package com.apress.prospring5.ch3.annotated;

import com.apress.prospring5.ch3.provider.MessageProvider;
import com.apress.prospring5.ch3.renderer.MessageRenderer;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("renderer")
public class StandardOutMessageRenderer implements MessageRenderer {

  private MessageProvider messageProvider;

  @Override
  public void render() {
    if (messageProvider == null) {
      throw new RuntimeException(
          "You must set the property messageProvider of class:"
              + StandardOutMessageRenderer.class.getName());
    }

    System.out.println(messageProvider.getMessage());
  }

  @Override
  public MessageProvider getMessageProvider() {
    return this.messageProvider;
  }

  @Override
  @Autowired
  public void setMessageProvider(MessageProvider provider) {
    this.messageProvider = provider;
  }
}
