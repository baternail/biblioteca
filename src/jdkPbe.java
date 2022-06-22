import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;



public class jdkPbe {
	public static final String ALGORUTHM = "PBEWITHMD5andDES";
	/**
	 * Numero de iteraciones
	 */
	public static final int ITERATION_COUN = 100;
	
	/**
	 * Inicializacion de "salida aleatoria" <br>
	 * La longitud de la salida debe de ser de 8 bytes.
	 * @return byte [] con la salida aleatoria
	 * @throws Exeption
	 */
	
	public static byte[] initSalida() throws Exception {
		// instancia un numero aleatorio seguro
		SecureRandom random = new SecureRandom();
		// sal de salida 
		return random.generateSeed(8);
	}
	/**
	 *  Clave de Convercion 
	 *  @param contraseña contraseña
	 *  @ clave de retorno
	 *  @throws Exeption
	 */
	
	private static Key toKey(String password) throws Exception {
		// conversion de material clave
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		//instanciar
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORUTHM);
		// genere la clave
		SecretKey secretkey = keyFactory.generateSecret(keySpec);
		return secretkey;
	}
	
	/**
	 * cifrado
	 * @param data data
	 * @param clave de contraseña
	 * @param salida aleatoria
	 * @param byte [] datos cofrados
	 * @throws Exception
	 */
	
	public static byte[] encrypt(byte[] data, String password, byte[] salida) throws Exception {
		//clave conversion 
		Key key = toKey(password);
		//intsnaciar datos de referencia de PBE
		PBEParameterSpec parameterSpec = new PBEParameterSpec(salida, ITERATION_COUN);
		//intancias
		Cipher cipher = Cipher.getInstance(ALGORUTHM);
		//inizializar
		cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
		//ejecucion operacion
		return cipher.doFinal(data);
		}
	
	
}// fin de clase principal
