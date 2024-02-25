
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/*  This declares a public class named FileBlowfish,
 	which contains the main logic for file encryption and
	decryption.
*/
public class FileBlowfish {

	
	private static SecretKeySpec secretkey;
	private static byte[] key;
	
	public static void setKey(String mykey) {
	
		try {
			key = mykey.getBytes(); 
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); 
			secretkey = new SecretKeySpec(key, "Blowfish");
		} 
		catch (Exception e) {
			System.out.print(e);
		}
		
	}
	
	// Encryption
	public static void encrypt(String inputFilePath, String EncKey, String outputFilePath){ 
		try {
			setKey(EncKey);
			Cipher c = Cipher.getInstance("Blowfish");
			c.init(Cipher.ENCRYPT_MODE, secretkey);
			
			File inputFile = new File(inputFilePath);
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte inputBytes[] = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes); 
			
			byte outputBytes[] = c.doFinal(inputBytes);

			File outFile = new File(outputFilePath);
			if(outFile.createNewFile()) {
				System.out.println("File sucessfully created at: "+outputFilePath);
			}
			else {
				System.out.println("File already exists, overwrinting existing file");
			}
			
			FileOutputStream outputStream = new FileOutputStream(outFile);
			outputStream.write(outputBytes);

			outputStream.close();
			inputStream.close();
		} 
		catch (Exception e) {
			System.out.print(e);
		}	
	}
	
	// Decryption
	public static void decrypt(String inputFilePath, String EncKey, String outputFilePath){
		try {
			
			setKey(EncKey);
			Cipher c = Cipher.getInstance("Blowfish");
			c.init(Cipher.DECRYPT_MODE, secretkey);
			
			File inputFile = new File(inputFilePath);
			System.out.println(inputFile.length());
			
			FileInputStream inputStream = new FileInputStream(inputFile);
			byte inputBytes[] = new byte[(int) inputFile.length()];
			inputStream.read(inputBytes); 
			byte outputBytes[] = c.doFinal(inputBytes);
			
			File outFile = new File(outputFilePath);
			if(outFile.createNewFile()) {
				System.out.println("File sucessfully created at: "+outputFilePath);
			}
			else {
				System.out.println("File already exists, overwrinting existing file");
			}
		
			FileOutputStream outputStream = new FileOutputStream(outFile);
			outputStream.write(outputBytes);
			
			outputStream.close();
			inputStream.close();
		} 
		catch (Exception e) {
			System.out.print(e);
		}
	}
}
