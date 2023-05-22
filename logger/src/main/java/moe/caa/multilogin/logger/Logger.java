package moe.caa.multilogin.logger;

/**
 * 一个日志记录程序
 */
public interface Logger {

    /**
     * 记录一条日志
     *
     * @param level     日志级别
     * @param message   日志信息
     * @param throwable 栈信息
     */
    void log(Level level, String message, Throwable throwable);

    default void log(Level level, String message) {
        log(level, message, null);
    }

    default void log(Level level, Throwable throwable) {
        log(level, null, throwable);
    }

    default void trace(String message, Throwable throwable) {
        log(Level.TRACE, message, throwable);
    }

    default void trace(String message) {
        log(Level.TRACE, message);
    }

    default void trace(Throwable throwable) {
        log(Level.TRACE, null, throwable);
    }

    default void debug(String message, Throwable throwable) {
        log(Level.DEBUG, message, throwable);
    }

    default void debug(String message) {
        log(Level.DEBUG, message);
    }

    default void debug(Throwable throwable) {
        log(Level.DEBUG, null, throwable);
    }

    default void info(String message, Throwable throwable) {
        log(Level.INFO, message, throwable);
    }

    default void info(String message) {
        log(Level.INFO, message);
    }

    default void info(Throwable throwable) {
        log(Level.INFO, null, throwable);
    }

    default void warn(String message, Throwable throwable) {
        log(Level.WARN, message, throwable);
    }

    default void warn(String message) {
        log(Level.WARN, message);
    }

    default void warn(Throwable throwable) {
        log(Level.WARN, null, throwable);
    }

    default void error(String message, Throwable throwable) {
        log(Level.ERROR, message, throwable);
    }

    default void error(String message) {
        log(Level.ERROR, message);
    }

    default void error(Throwable throwable) {
        log(Level.ERROR, null, throwable);
    }

    default void fatal(String message, Throwable throwable) {
        log(Level.FATAL, message, throwable);
    }

    default void fatal(String message) {
        log(Level.FATAL, message);
    }

    default void fatal(Throwable throwable) {
        log(Level.FATAL, null, throwable);
    }
}