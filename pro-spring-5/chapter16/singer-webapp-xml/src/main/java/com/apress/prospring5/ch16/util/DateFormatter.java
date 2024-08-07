package com.apress.prospring5.ch16.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {

  public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  public Date parse(String s, Locale locale) throws ParseException {
    return formatter.parse(s);
  }

  @Override
  public String print(Date date, Locale locale) {
    return formatter.format(date);
  }

}
