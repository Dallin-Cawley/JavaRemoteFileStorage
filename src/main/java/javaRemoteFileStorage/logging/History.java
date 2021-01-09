package javaRemoteFileStorage.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * History
 *
 * Represents the history of the actions the Server
 * took on request of the client.
 *
 * @author Dallin Cawley
 * @since 12/5/2020
 */
public class History {
    protected ArrayList<LoggingEvent> historyStack;
    protected final FileLogger fileLogger;
    private final File logFile;

    /**
     * History Constructor
     *
     * Constructs the History object. Each History has a logger
     * which is initialized with the passed in className.
     *
     * @param fileLocation The location of the log file
     */
    public History(String fileLocation) {
        this.historyStack = new ArrayList<>();
        this.logFile = new File(fileLocation);
        this.fileLogger = new FileLogger(logFile);
    }

    /**
     * addEvent
     *
     * Adds an action that the Server took on behalf of the client
     * and stores it in the historyStack.
     *
     * @param event The LoggingEvent.
     */
    public void addEvent(LoggingEvent event) {
        historyStack.add(event);
    }

    /**
     * endHistory
     *
     * Once a History has been completely logged, two new line characters
     * are inserted into the logFile for readability.
     *
     * @throws IOException Thrown if the FileWriter encounters an error.
     */
    public void endHistory() throws IOException {
        FileWriter logFileWriter = new FileWriter(logFile, true);
        logFileWriter.write("\n\n");
        logFileWriter.close();
    }

    /**
     * hasHistory
     *
     * Determines of there is any history.
     *
     * @return True if the historyStack has LoggingEvents, false otherwise.
     */
    public boolean hasHistory() {
        return !historyStack.isEmpty();
    }
}
