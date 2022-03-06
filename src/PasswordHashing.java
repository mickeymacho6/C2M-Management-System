import java.math.BigInteger;
import java.util.*;
import java.security.*;

public class PasswordHashing {
    private String password;
    public PasswordHashing(String password) {
        this.password = password;

    }

    //MD5
    public static String getHashedPassword(String hashAlgorithm, String in) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(hashAlgorithm);

        byte [] msgDigest = md.digest(in.getBytes());
        BigInteger num = new BigInteger(1, msgDigest);
        String hashText = num.toString(16);

        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }

        return hashText;
    }

    //Secure Hash Algorithm (SHA)
    //Password-Based Key Derivative Function (PBKDF2)



    public static void main(String args[]) throws NoSuchAlgorithmException
    {
        String str = "hello world";
        System.out.println("MD5: " + getHashedPassword("MD5", str));
        System.out.println("SHA-1: " + getHashedPassword("SHA-1", str));

    }
}
