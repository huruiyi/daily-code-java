package com.javacodegeeks.examples.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class MultipartExceptionHandler {

  @ExceptionHandler(MultipartException.class)
  public String handleError(MultipartException e, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("error", e.getCause().getMessage());
    return "redirect:/uploadForm";
  }

}
