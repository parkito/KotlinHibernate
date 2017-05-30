package com.roommate.basecontrol.utils.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Artem Karnov @date 11.11.2016.
 *         artem.karnov@t-systems.com
 **/
public class UserNotFoundException extends DAOException {
    private static Logger logger = LogManager.getLogger(UserNotFoundException.class);

    /**
     * Exception with message for situation when user wasn't found
     *
     * @param message message for exception
     */
    public UserNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }

    /**
     * Exception with message and throwable for situation when user wasn't found
     *
     * @param message   message for exception
     * @param throwable object for exception
     */
    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
        logger.warn(message, throwable);
    }
}
