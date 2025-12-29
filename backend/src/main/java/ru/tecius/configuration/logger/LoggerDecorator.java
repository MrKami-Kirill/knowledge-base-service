package ru.tecius.configuration.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerDecorator {

    private static final Logger LOG = LogManager.getLogger(LoggerDecorator.class);

    public void info(String message) {
        LOG.info(message);
    }

    public void warn(String message) {
        LOG.warn(message);
    }

    public void debug(String message) {
        LOG.debug(message);
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void error(Throwable ex) {
        LOG.error(ex);
    }

    public void error(String message, Throwable ex) {
        LOG.error(message, ex);
    }

}
