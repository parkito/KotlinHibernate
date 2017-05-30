package com.roommate.basecontrol.utils.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Artem Karnov @date 17.11.2016.
 *         artem.karnov@t-systems.com
 **/
public class RoomNotFoundException extends DAOException {
    private static Logger logger = LogManager.getLogger(RoomNotFoundException.class);

    /**
     * Exception with message for situation when room wasn't found
     *
     * @param message message for exception
     */
    public RoomNotFoundException(String message) {
        super(message);
        logger.warn(message);
    }

    /**
     * Exception with message and throwable for situation when room wasn't found
     *
     * @param message   message for exception
     * @param throwable object for exception
     */
    public RoomNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
        logger.warn(message, throwable);

    }
}
