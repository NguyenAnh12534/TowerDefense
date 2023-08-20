package vn.ha.tower_defense.exceptions;

public interface ExceptionHandler {

    public void handle(String contextCode, String errorCode,
            String errorText, Throwable t);

    public void raise(String contextCode, String errorCode,
            String errorText);
}
