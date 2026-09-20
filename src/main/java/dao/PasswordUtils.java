package dao;

import jakarta.enterprise.context.ApplicationScoped;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@ApplicationScoped
public class PasswordUtils {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    // Genera un hash sicuro per la password
    public static String hashPassword(String password) {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        KeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Concatenazione del salt + ":" + hash, entrambi codificati in Base64
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Errore nella generazione dell'hash", e);
        } finally {
            ((PBEKeySpec) spec).clearPassword();
        }
    }


    // Verifica la password con l'hash
    public static boolean checkPassword(String password, String storedHash) {
        try {
            // Verifica se la stringa contiene il separatore ":"
            if (storedHash == null || !storedHash.contains(":")) {
                throw new IllegalArgumentException("Il formato dell'hash è errato.");
            }

            // Separare il sale e l'hash dalla stringa memorizzata
            String[] parts = storedHash.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Il formato dell'hash è errato, deve essere <salt>:<hash>");
            }

            String storedSaltBase64 = parts[0];
            String storedHashBase64 = parts[1];

            // Decodifica il sale e l'hash in Base64
            byte[] salt = Base64.getDecoder().decode(storedSaltBase64);
            byte[] storedHashBytes = Base64.getDecoder().decode(storedHashBase64);

            // Crea un PBEKeySpec con la password e il sale
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);

            // Crea una SecretKeyFactory per generare l'hash
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);

            // Genera l'hash della password
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Confronta l'hash generato con quello memorizzato nel database
            return java.util.Arrays.equals(hash, storedHashBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Errore nella verifica della password", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Errore nel formato dell'hash: " + e.getMessage(), e);
        }
    }


    private static byte[] getSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }
}