// Kody Manastyrski
// Hashing Method
// 13/06/19

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Scanner;

public class hasher{

	private SecureRandom rnd;
	private MessageDigest md;
	public hasher(){

		rnd = new SecureRandom();
		try {
			md = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e){
			System.out.println("Could not find algorithm " + e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	/**
	 * A method to salt and hash a given password
	 * @param passwd a string to be hashed
	 * @return a string obtained from salting and hashing the password with MD5
	 */
	private String encode (String passwd){
		byte[] salt = new byte[16];
		rnd.nextBytes(salt);
		return encode(passwd, salt);
	}

	/**
	 * A method to hash a given salt and password
	 * @param passwd a string to be hashed
	 * @param salt a set of 16 bytes to be used as salt for the hash
	 * @return a string obtained from hashing the salt and password with MD5
	 */
	public String encode(String passwd, byte[] salt){
		try {
			String hash;
			md.update(passwd.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i += 1) {
				sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
			}
			hash = sb.toString();
			return hash;
		}
		catch (Exception e){
			System.out.println("Could not complete method: " + e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}
	}

	public static void main(String[] args){
		hasher h = new hasher();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a password to be salted and hashed: ");
		String pass = in.nextLine();
		System.out.println(h.encode(pass));
		byte[] s = ("abcdefghijklmnop").getBytes();
		System.out.println("Hashing password cat with salt " + s);
		System.out.println(h.encode("cat", s));
	}


}
