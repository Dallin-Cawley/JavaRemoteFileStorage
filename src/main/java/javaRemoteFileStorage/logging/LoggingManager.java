package javaRemoteFileStorage.logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class LoggingManager {
    ArrayList<History> histories;
    LoggerFormatter formatter;
    History loggingManagerHistory;

    public LoggingManager() {
        histories = new ArrayList<>();
        formatter = new LoggerFormatter();
        loggingManagerHistory = new History(System.getenv("LOG_FILE_LOCATION"));
    }

    public void logHistories() {

        try {
            for (History history : histories) {
                for (LoggingEvent loggingEvent : history.historyStack) {
                    history.fileLogger.log(loggingEvent);
                }
                history.endHistory();
            }
        }
        catch (IOException exception) {
            String loggingMessage = "Unable to log histories: " + Arrays.toString(exception.getStackTrace());
            LoggingEvent event = new LoggingEvent(Level.SEVERE, loggingMessage, "logHistories()", LoggingManager.class.getName());
            loggingManagerHistory.addEvent(event);
        }
    }

    public void addHistory(History history) {
        if (!histories.contains(history)) {
            histories.add(history);
        }
    }
}
