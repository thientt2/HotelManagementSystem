package system;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encode {
	public static String base64Encode(String plainText) {
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(plainTextBytes);
    }

    // Method to hash a string with MD5
    public static String md5Hash(String input) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            byte[] bytes = md5Digest.digest(input.getBytes(StandardCharsets.UTF_8));

            for (byte b : bytes) {
                hash.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

    // Method to base64 encode a string and then hash the result with MD5
    public static String base64EncodeAndMd5Hash(String input) {
        // Encode the input with Base64
        String base64Encoded = base64Encode(input);
        // Hash the Base64 encoded string with MD5
        String hashed = md5Hash(base64Encoded);

        return hashed;
    }
}
