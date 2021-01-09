package remoteStorageServer.Communication.Security;

import java.io.*;
import java.security.*;

public class DigitalSignature {

    private byte[] digitalSignature;

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
            }
            catch (NoSuchAlgorithmException exception) {
                //TODO: Add exception handling that sends a message to the client indicating the contents were unable to
                //TODO: be signed.
                exception.printStackTrace();
            }
            catch(InvalidKeyException exception) {
                //TODO: Send message to client indicating that the key is invalid
                System.out.println("Invalid Key for " + file.getAbsolutePath());
                exception.printStackTrace();
            } catch (IOException e) {
                //TODO: Notify client that there was an error retrieving file
                System.out.println("Unable to retrieve file for signing.");
                System.out.println("\t" + file.getAbsolutePath());
            } catch (SignatureException e) {
                //TODO: Notify client that an error occurred while signing file.
                System.out.println("Unable to update signature object.");
                System.out.println("\t" + file.getAbsolutePath());
            }
        }
    }

    public byte[] getSignature() throws SignatureException {
        return digitalSignature;
    }
}
