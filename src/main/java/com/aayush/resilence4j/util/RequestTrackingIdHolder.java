package com.aayush.resilence4j.util;

public class RequestTrackingIdHolder {
  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static String getRequestTrackingId() {
    return threadLocal.get();
  }

  public static void setRequestTrackingId(String id) {
    if (threadLocal.get() != null) {
      threadLocal.set(null);
      threadLocal.remove();
    }
    threadLocal.set(id);
  }

  public static void clear() {
    threadLocal.set(null);
    threadLocal.remove();
  }

}
