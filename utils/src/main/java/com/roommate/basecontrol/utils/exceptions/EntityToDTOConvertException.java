package com.roommate.basecontrol.utils.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Artem Karnov @date 30.01.17.
 *         artem.karnov@t-systems.com
 */

public class EntityToDTOConvertException extends DAOException {
    private static Logger logger = LogManager.getLogger(EntityToDTOConvertException.class);

    /**
     * Exception with message for situation when there is error in EntityToDTOConverter
     *
     * @param message message for exception
     */
    public EntityToDTOConvertException(String message) {
        super(message);
        logger.warn(message);
    }

    /**
     * Exception with message and throwable for situation when there is error in EntityToDTOConverter
     *
     * @param message   message for exception
     * @param throwable object for exception
     */
    public EntityToDTOConvertException(String message, Throwable throwable) {
        super(message, throwable);
        logger.warn(message, throwable);
    }
}
