package it.pegasoft.usersecurity.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogExample {
    
    public void givenLoggerWithDefaultConfig() throws Exception {
        Logger logger = LogManager.getLogger(getClass());
        Exception e = new RuntimeException("This is only a test!");

        logger.info("This is a simple message at INFO level. " +
                "It will be hidden.");
        logger.error("This is a simple message at ERROR level. " +
                "This is the minimum visible level.", e);
    }
}
