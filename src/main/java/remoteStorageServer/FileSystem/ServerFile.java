package remoteStorageServer.FileSystem;

import remoteStorageServer.Communication.Security.DigitalSignature;
import remoteStorageServer.Communication.Security.KeyManager;

import java.io.File;
import java.security.Signature;
import java.security.SignatureException;

public class ServerFile {
    private final File file;
    private final DigitalSignature digitalSignature;

    public ServerFile(String fileLocation, KeyManager keyManager) {
        file = new File(fileLocation);
        digitalSignature = new DigitalSignature(file, keyManager);
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


}
