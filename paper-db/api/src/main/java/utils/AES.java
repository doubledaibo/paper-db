package utils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import configs.Config;

public enum AES {
	AUTH(Config.AUTH_KEY), PASSWD(Config.PASSWD_KEY);
	private String salt = "miaoxiaoer";
	private int iterationCount = 65536;
	private int keyLength = 128;
	private SecretKey secretKey = null;

	/**
	 * Generate secret key from password.
	 * 
	 * @param password
	 */
	AES(String password) {
		// Generate spec from password.
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),
				salt.getBytes(), iterationCount, keyLength);
		SecretKey secret = null;
		try {
			secret = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
					.generateSecret(spec);
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
		}
		secretKey = new SecretKeySpec(secret.getEncoded(), "AES");
	}
	
	public String encrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] ansByte = cipher.doFinal(text.getBytes());
		String ansText = Base64.getEncoder().encodeToString(ansByte);
		return ansText;
	}

	public String decrypt(String text) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] textByte = Base64.getDecoder().decode(text);
		String ansText = new String(cipher.doFinal(textByte));
		return ansText;
	}
}