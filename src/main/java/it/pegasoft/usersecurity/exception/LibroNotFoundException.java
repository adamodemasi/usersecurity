package it.pegasoft.usersecurity.exception;

import it.pegasoft.usersecurity.logging.LogExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LibroNotFoundException extends RuntimeException{
    static final Logger logger = LogManager.getLogger(LogExample.class);
    public LibroNotFoundException(String msg){
        super(msg);
        logger.error("Book not found");
    }
}