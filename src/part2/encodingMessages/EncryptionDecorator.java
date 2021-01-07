package part2.encodingMessages;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *  Encryption Decorator
 */
public class EncryptionDecorator extends ProcessingStrategy{
    /**
     * EncryptionDecorator Constructor
     * @param mailStore mailStore to encrypt
     */
    public EncryptionDecorator(MailStoreInterface mailStore) {
        super(mailStore);
    }

    /**
     * Method to code the body
     * @param body body to encrypt
     * @return Body encrypted
     */
    public String code(String body) {
        String key = "IWantToPassTAP12"; // 128 bit key
        java.security.Key aesKey =
                new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        byte[] encrypted = new byte[0];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(body.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encrypted);
    }
    /**
     * Method to code the body
     * @param body Body to decode
     * @return Decoded body
     */
    public String decode(String body) {
        String key = "IWantToPassTAP12"; // 128 bit key
        java.security.Key aesKey =
                new javax.crypto.spec.SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }

        byte[] encrypted = Base64.getDecoder().decode(body.getBytes());
        String decrypted = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;
    }
}
