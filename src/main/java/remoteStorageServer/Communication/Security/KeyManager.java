package remoteStorageServer.Communication.Security;

import java.security.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class KeyManager {
    private KeyPairGenerator keyPairGenerator;
    private KeyPair keyPair;
    private ZonedDateTime previousUpdate;

    private static final int KEY_SIZE = 1024;

    public KeyManager() {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyPairGenerator.initialize(KEY_SIZE, random);
        }
        catch (NoSuchProviderException | NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
    }

    public boolean generateKeyPair() {

        if (keyPairGenerator != null) {
            keyPair = keyPairGenerator.generateKeyPair();
            previousUpdate = ZonedDateTime.now(ZoneId.of("America/Denver"));
            return true;
        }
        else {
            System.out.println("keyPairGenerator was null");
            keyPair = null;
            return false;
        }
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    private boolean needsUpdate() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Denver"));

        return now.minusDays(7).equals(previousUpdate);
    }

    public void updateKeyPair() {
        if (needsUpdate()) {
            previousUpdate = ZonedDateTime.now(ZoneId.of("America/Denver"));
            generateKeyPair();
        }
    }
}
