package Shakti.HibernateApp.services;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class CryptService {
	private byte[] toBytes(String str) {
		return str.getBytes(StandardCharsets.UTF_8);
	}
	
	private byte[] randomBytes(int n) {
        byte bytes[] = new byte[n];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }

	public String encode64(byte[] input) {
        return Base64.encodeBase64String(input);
    }
	
	public byte[] decode64(String coded) {
		return Base64.decodeBase64(coded);
	}

/*
public byte[] getHashWithSalt(String input, HashingTechqniue technique, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(technique.value);
        digest.reset();
        digest.update(salt);
        byte[] hashedBytes = digest.digest(stringToByte(input));
        return hashedBytes;
    }
    */
}
