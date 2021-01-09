package javaRemoteFileStorage.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileLogger
 *
 * Logs events in a specified file.
 *
 * @author Dallin Cawley
 * @since 12/5/2020
 */
public class FileLogger {
    private static final LoggerFormatter loggerFormatter = new LoggerFormatter();
    private final File logFile;

    /**
     * FileLogger Constructor
     *
     * Constructs the FileLogger initializing the File where all the logs
     * will be contained.
     *
     * @param logFile The file which where the logs will be written.
     */
    public FileLogger(File logFile) {
        this.logFile = logFile;
    }

    /**
     * log
     *
     * Logs the LoggingEvent in the class logFile formatted by the
     * LoggerFormatter.
     *
     * @param event The LoggingEvent that will be recorded
     * @throws IOException Thrown if the FileWriter encounters an error.
     */
    public void log(LoggingEvent event) throws IOException {
        String message = loggerFormatter.format(event);

        FileWriter logFileWriter = new FileWriter(logFile, true);
        logFileWriter.write(message);
        logFileWriter.close();
    }
}
