package online.quar.application.manager;

import online.quar.application.Constants;
import online.quar.application.Singleton;
import online.quar.application.model.User;
import online.quar.application.util.Logger;

import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class AuthenticationManager {
    private Logger log = Singleton.getLogger();

    public boolean authenticateUser(User u, String pass) {
        byte[] testedPass = getPasswordHash(pass, Constants.PASSWORD_SALT);
        if (testedPass == null)
            return false;
        byte[] userPass = u.getPassword();
        return userPass == testedPass;
    }

    //Adapted from https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
    public String getPasswordHashToString(String passwordToHash, String salt) {
        String generatedPassword = null;
        byte[] bytes = getPasswordHash(passwordToHash, salt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        generatedPassword = sb.toString();
        if(generatedPassword != null)
            return generatedPassword;
        else
            throw new UnsupportedOperationException("Password could not be hashed");
    }

    public byte[] getPasswordHash(String passwordToHash, String salt) {
        String generatedPassword = null;
        byte[] passwordHash = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt.getBytes());
            //Get the hash's bytes
            passwordHash = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
        }
        catch (NoSuchAlgorithmException e) {
            log.c(e.getMessage() + "\n" + e.getStackTrace());
        }
//        return generatedPassword;
        if (passwordHash != null)
            return passwordHash;
        else
            throw new UnsupportedOperationException("Password could not be hashed");
    }

    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
