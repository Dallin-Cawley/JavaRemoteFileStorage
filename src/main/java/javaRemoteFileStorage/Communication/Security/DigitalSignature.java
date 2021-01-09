package javaRemoteFileStorage.Communication.Security;

import javaRemoteFileStorage.logging.LoggingEvent;

import java.io.*;
import java.security.*;
import java.util.logging.Level;

public class DigitalSignature {

    private byte[] digitalSignature;
    private LoggingEvent loggingEvent;

    public DigitalSignature(File file, KeyManager keyManager) {
        if (keyManager.generateKeyPair()) {
            System.out.println("Successfully generated key pair");
            try {
                Signature signature = Signature.getInstance("SHA256withDSA");
                signature.initSign(keyManager.getPrivateKey());

                //Get the File Bytes and update signature
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                int fileBufferSize = 1024;
                byte[] buffer = new byte[fileBufferSize];
                int length;
                while ((length = bufferedInputStream.read(buffer)) >= 0) {
                    signature.update(buffer, 0, length);
                }
                bufferedInputStream.close();

                digitalSignature = signature.sign();
                String loggingMessage = "Successfully signed " + file.getAbsolutePath();
                loggingEvent = new LoggingEvent(Level.INFO, loggingMessage, "DigitalSignature()", DigitalSignature.class.getName());
            }
            catch (NoSuchAlgorithmException exception) {
                //TODO: Add exception handling that sends a message to the client indicating the contents were unable to
                //TODO: be signed.
                exception.printStackTrace();
                String loggingMessage = "Unable to sign " + file.getAbsolutePath() + ". Encountered a NoSuchAlgorithmException.";
                loggingEvent = new LoggingEvent(Level.INFO, loggingMessage, "DigitalSignature()", DigitalSignature.class.getName());
            }
            catch(InvalidKeyException exception) {
                //TODO: Send message to client indicating that the key is invalid
                exception.printStackTrace();
                String loggingMessage = "Unable to sign " + file.getAbsolutePath() + ". Encountered a InvalidKeyException.";
                loggingEvent = new LoggingEvent(Level.INFO, loggingMessage, "DigitalSignature()", DigitalSignature.class.getName());
            } catch (IOException e) {
                //TODO: Notify client that there was an error retrieving file
                String loggingMessage = "Unable to sign " + file.getAbsolutePath() + ". Encountered a IOException.";
                loggingEvent = new LoggingEvent(Level.INFO, loggingMessage, "DigitalSignature()", DigitalSignature.class.getName());
            } catch (SignatureException e) {
                //TODO: Notify client that an error occurred while signing file.
                String loggingMessage = "Unable to sign " + file.getAbsolutePath() + ". Encountered a SignatureException.";
                loggingEvent = new LoggingEvent(Level.INFO, loggingMessage, "DigitalSignature()", DigitalSignature.class.getName());
            }
        }
    }

    public byte[] getSignature() throws SignatureException {
        return digitalSignature;
    }

    public LoggingEvent getLoggingEvent() {
        return loggingEvent;
    }
}
