package javaRemoteFileStorage.logging;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * LoggerFormatter
 *
 * Formats a LoggingEvent for the FileLogger
 *
 * @author Dallin Cawley
 * @since 12/5/2020
 */
public class LoggerFormatter {

    /**
     * format
     *
     * Formats the passed in LoggingEvent to the format of....
     *
     * [ Level ] { Origin Class Name and Method} - Time - Message\n
     *
     * @param event The LoggingEvent to be formatted
     * @return The formatted LoggingEvent
     */
    public String format(LoggingEvent event) {
        StringBuilder stringBuilder = new StringBuilder();

        Date date = new Date();
        date.setTime(event.milliseconds);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MM/dd/yyyy hh:mm:ss aa");
        String formattedDate = dateFormatter.format(date);

        stringBuilder.append(event.level).append(" { ");
        stringBuilder.append(event.className).append(".").append(event.methodName).append(" } - ");
        stringBuilder.append(formattedDate).append(" - ");
        stringBuilder.append(event.message).append('\n');

        return stringBuilder.toString();
    }

}
