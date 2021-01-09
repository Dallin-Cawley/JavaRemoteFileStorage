package javaRemoteFileStorage.FileSystem;

import javaRemoteFileStorage.Communication.Security.DigitalSignature;
import javaRemoteFileStorage.Communication.Security.KeyManager;
import javaRemoteFileStorage.logging.History;
import javaRemoteFileStorage.logging.LoggingEvent;

import java.io.File;
import java.security.SignatureException;

public class ServerFile {
    private final File file;
    private final DigitalSignature digitalSignature;
    private final History history;

    public ServerFile(String fileLocation, KeyManager keyManager) {
        file = new File(fileLocation);
        digitalSignature = new DigitalSignature(file, keyManager);
        history = new History(System.getenv("LOG_FILE_LOCATION"));
        history.addEvent(digitalSignature.getLoggingEvent());
    }

    public byte[] getSignature() {
        try {
            return digitalSignature.getSignature();
        }
        catch(SignatureException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public int getFileSizeAsInt() {
        return Float.floatToIntBits(file.length());
    }

    public History getHistory() {
        return history;
    }

    public void addLoggingEvent(LoggingEvent event) {
        history.addEvent(event);
    }
}
