package com.apress.prospring4.ch2.Hello2;

import com.apress.prospring4.ch2.Hello2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessagePrinter {

  final private MessageService service;

  @Autowired
  public MessagePrinter(MessageService service) {
    this.service = service;
  }

  public void printMessage() {
    System.out.println(this.service.getMessage());
  }
}