package moe.caa.multilogin.logger.bridges;


import moe.caa.multilogin.logger.Level;
import moe.caa.multilogin.logger.Logger;
import moe.caa.multilogin.logger.LoggerProvider;

/**
 * 调试日志处理
 */
public class DebugLoggerBridge implements Logger {
    private final Logger logger;

    public DebugLoggerBridge(Logger logger) {
        this.logger = logger;
    }

    public static void startDebugMode() {
        if (!(LoggerProvider.getLogger() instanceof DebugLoggerBridge)) {
            LoggerProvider.setLogger(new DebugLoggerBridge(LoggerProvider.getLogger()));
        }
    }

    public static void cancelDebugMode() {

        if (LoggerProvider.getLogger() instanceof DebugLoggerBridge) {
            LoggerProvider.setLogger(((DebugLoggerBridge) LoggerProvider.getLogger()).logger);
        }
    }

    @Override
    public void log(Level level, String message, Throwable throwable) {
        if (level == Level.DEBUG || level == Level.TRACE) {
            level = Level.INFO;
            message = "[DEBUG] " + message;
        }
        logger.log(level, message, throwable);
    }
}