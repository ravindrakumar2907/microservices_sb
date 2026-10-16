package com.tech.gateway.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.spec.SecretKeySpec;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author RAVI
 */
public class Utils {

	private static final Logger logger = LoggerFactory.getLogger(Utils.class);

	private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int CODE_LENGTH = 6; // You can adjust the code length as needed

	/**
	 * Convert Basic 64 bit Authentication from username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static String basicAuthentication(String username, String password) {
		return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
	}

	/**
	 * Encode String
	 * 
	 * @param string
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeString(String string, String encoding) throws UnsupportedEncodingException {
		return new String(string.getBytes(), encoding);
	}

	/**
	 * Decode String
	 * 
	 * @param string
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeString(String string, String encoding) throws UnsupportedEncodingException {
		return new String(string.getBytes(encoding), "utf-8");
	}

	/**
	 * Convert text to Hash as per Algo info passed
	 * 
	 * @param text
	 * @param algorithm
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashText(String text, String algorithm) throws NoSuchAlgorithmException {
		Hashtable<String, String> algorithms = new Hashtable<>();
		algorithms.put("md5", "MD5");
		algorithms.put("sha1", "SHA-1");
		algorithms.put("sha256", "SHA-256");

		String hashAlgorithm = algorithms.get(algorithm);
		if (hashAlgorithm == null) {
			throw new IllegalArgumentException("Invalid hash algorithm: " + algorithm);
		}

		byte[] digest = MessageDigest.getInstance(hashAlgorithm).digest(text.getBytes());
		return DatatypeConverter.printHexBinary(digest).toUpperCase();
	}
	
	/***
	 * create Sha256 hash
	 * @param textToHash
	 * @param hashAlgorithm
	 * @return
	 */
	public static String createSha256hash(String textToHash, String hashAlgorithm)
    {
		String generatedHash = null;
        try {
        	MessageDigest md = null;
        	if( hashAlgorithm.equalsIgnoreCase("sha256")){
        		md = MessageDigest.getInstance("SHA-256");
        	}else if( hashAlgorithm.equalsIgnoreCase("sha384")){
        		md = MessageDigest.getInstance("SHA-384");
        	}else if( hashAlgorithm.equalsIgnoreCase("sha512")){
        		md = MessageDigest.getInstance("SHA-512");
        	}
        	md = MessageDigest.getInstance("SHA-256");
        	byte[] bytes = md.digest(textToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedHash = sb.toString();
        }catch (NoSuchAlgorithmException e){
        	logger.error("NoSuchAlgorithmException in createSha256hash: ", e);
        }catch (Exception e){
        	logger.error("Exception in createSha256hash: ", e);            
        }
        return generatedHash;
    }
	
	/***
	 * create MD5 Hash
	 * @param password
	 * @return
	 */
	public static String createMD5Hash(String password){
		String md5Password = "";
		try {
			byte[] defaultBytes = password.getBytes();

			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();

			algorithm.update(defaultBytes);

			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			md5Password = hexString.toString();
			return md5Password;
		} catch (Exception nsae) {
			logger.error("Exception in createMD5Hash: ", nsae);
			return null;
		}
		
	}
	

	/***
	 * JSON to map conversion
	 * 
	 * @param jsonFile
	 * @return
	 */
	public static Map<String, Object> convertJsonIntoMap(String jsonFile) {
		Map<String, Object> map = new HashMap<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			mapper.readValue(jsonFile, new TypeReference<Map<String, Object>>() {
			});
			map = mapper.readValue(jsonFile, new TypeReference<Map<String, Object>>() {
			});
		} catch (IOException e) {
			logger.error("IOException while converting JSON to Map: ", e);
		}
		return map;
	}

	// https://www.baeldung.com/java-secure-aes-key

	private static Key getSecureRandomKey(String cipher, int keySize) {
		byte[] secureRandomKeyBytes = new byte[keySize / 8];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(secureRandomKeyBytes);
		return new SecretKeySpec(secureRandomKeyBytes, cipher);
	}

	public static String convertSecretKeyToString(Key key) throws NoSuchAlgorithmException {
		byte[] rawData = key.getEncoded();
		String encodedKey = Base64.getEncoder().encodeToString(rawData);
		return encodedKey;
	}

	private static final String CIPHER = "AES";

	public static String getSecureKey() throws NoSuchAlgorithmException {
		Key key = getSecureRandomKey(CIPHER, 256);
		return convertSecretKeyToString(key);
	}



	/***
	 * Convert distance to proper format as per decimalPlaces
	 * 
	 * @param distance
	 * @param decimalPlaces
	 * @return
	 */
	public static String parseAndFormatDistance(double distance, int decimalPlaces) {
		// Create a DecimalFormat object to format the distance
		String pattern = "0.";
		for (int i = 0; i < decimalPlaces; i++) {
			pattern += "0";
		}
		DecimalFormat decimalFormat = new DecimalFormat(pattern);

		// Format the distance with the specified number of decimal places
		String formattedDistance = decimalFormat.format(distance);

		return formattedDistance;
	}

	public static double convertMilesToKilometers(double miles) {
		// Conversion factor: 1 mile = 1.60934 kilometers
		return miles * 1.60934;
	}

	public static double convertMilesToMeters(double miles) {
		// Conversion factor: 1 mile = 1609.34 meters
		return miles * 1609.34;
	}

	public static double convertKilometersToMiles(double km) {
		if (km == 0)
			return 0;
		return (km / 1.609344);
	}

	public static double convertKilometersToMeters(double kilometers) {
		// Conversion factor: 1 kilometer = 1000 meters
		return kilometers * 1000.0;
	}

	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
	
	/***
	 * https://www.baeldung.com/java-email-validation-regex
	 * https://www.javatpoint.com/java-email-validation
	 * Validate email address
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(final String email) {
		final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return patternMatches(email, regexPattern);
	}

	
	/***
	 * 

	 * @return
	 */
	public static boolean isValidMobileNumber(final String mobileNumber) {
		final String regexPattern = "\\+\\d(-\\d{3}){2}-\\d{4}";
		return mobileNumber.matches(regexPattern);
	}
	
	


	public static boolean isValidEmailAddress(String email) {
		return false;
	}
	
	public static JsonNode parse(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("deprecation")
		JsonFactory factory = mapper.getJsonFactory();
		JsonNode actualObj =  null;
		try
		{
			@SuppressWarnings("deprecation")
			JsonParser parser = factory.createJsonParser(jsonString);	    
			actualObj = mapper.readTree(parser);	   
			logger.debug("Object: {}", actualObj); 
		}
		catch(Exception e)
		{
			logger.error("Error while parsing JSON: ", e); 
		}
		return actualObj;
	}
	
}
