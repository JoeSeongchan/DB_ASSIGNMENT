package utilities;

public final class Utilities {

  // function to log.
  public static void log(String msg) {
    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
    String CLASS_TAG = "CLASS : " + stackTraceElement.getClassName() + "\n";
    String callerFuncName = stackTraceElement.getMethodName();
    final String FUNC_TAG = "FN : " + callerFuncName + "\n";
    final String MSG = "MSG : " + msg;
    System.out.println("--------------------------");
    System.out.println(CLASS_TAG + FUNC_TAG + MSG);
  }

  public static void logThread() {
    StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
    String currentThreadName = Thread.currentThread().getName();
    String callerClassName = stackTraceElement.getClassName();
    int lastIndexOfDot = callerClassName.lastIndexOf(".");
    int endIdx = callerClassName.length();
    final String CLASS_TAG = callerClassName.substring(lastIndexOfDot, endIdx) + "_R\n";
    String callerFuncName = stackTraceElement.getMethodName();
    final String FUNC_TAG = "FN : " + callerFuncName + "\n";
    final String MSG = "THREAD : " + currentThreadName;
    System.out.println(CLASS_TAG + FUNC_TAG + MSG);
  }

  // function to make log string.
  public static String makeLog(String msg) {
    String callerClassName = Thread.currentThread().getStackTrace()[2].getClassName();
    int lastIndexOfDot = callerClassName.lastIndexOf(".");
    int endIdx = callerClassName.length();
    final String TAG = callerClassName.substring(lastIndexOfDot, endIdx) + "_R\n";
    String callerFuncName = Thread.currentThread().getStackTrace()[2].getMethodName();
    final String FUNC_TAG = "FN : " + callerFuncName + "\n";
    final String MSG = "MSG : " + msg;
    return TAG + FUNC_TAG + MSG;
  }
}
