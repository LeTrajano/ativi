import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hash {

    public static byte[] combinaSenhaComSalt(byte[] senha, byte[] salt) {
        try {
            // Combinando senha e o salt
            byte[] combined = new byte[senha.length + salt.length];
            System.arraycopy(senha, 0, combined, 0, senha.length);
            System.arraycopy(salt, 0, combined, senha.length, salt.length);

            // Usando SHA-256 para gerar o hash
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return sha256.digest(combined);

        } catch (NoSuchAlgorithmException e) {
            // Tratando a exceção adequadamente (pode lançar NoSuchAlgorithmException)
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] gerarSalt(int tamanho) {
        byte[] salt = new byte[tamanho];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) {
        // Exemplo
        String senhaOriginal = "1234";
        byte[] senhaBytes = senhaOriginal.getBytes();

        // Gerando um salt aleatório
        byte[] salt = gerarSalt(16);

        // Combinando a senha com o salt e gere o hash
        byte[] hashSenhaComSalt = combinaSenhaComSalt(senhaBytes, salt);

        // Imprimindo os resultados
        System.out.println("Senha Original: " + senhaOriginal);
        System.out.println("Salt: " + bytesToHex(salt));
        System.out.println("Hash da Senha com Salt: " + bytesToHex(hashSenhaComSalt));
    }

    // Convertendo bytes para hexadecimal
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
