package br.com.sennatech.sddo.customers.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

  private ExceptionUtil() {}

  public static String stackTraceToString(Exception e) {
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }
}
