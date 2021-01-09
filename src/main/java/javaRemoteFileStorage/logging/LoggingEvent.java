package javaRemoteFileStorage.logging;

import java.util.logging.Level;

/**
 * LoggingEvent
 *
 * Represents an action the server took on request
 * of the client.
 *
 * @author Dallin Cawley
 * @since 12/5/2020
 */
public class LoggingEvent {
    protected Level level;
    protected String message;
    protected String methodName;
    protected String className;

    protected long milliseconds;

    /**
     * LoggingEvent Constructor
     *
     * Initializes all the passed in variables and stores the time
     * in which the event took place.
     *
     * @param level The Level of the LoggingEvent.
     * @param message The action that the server took on request of the client.
     * @param methodName The method in which the action took place.
     */
    public LoggingEvent(Level level, String message, String methodName, String className) {
        this.level = level;
        this.message = message;
        this.methodName = methodName;
        this.className = className;
        this.milliseconds = java.lang.System.currentTimeMillis();

    }
}
