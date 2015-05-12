package com.david.cryptography.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CryptoTest 
    extends TestCase
{
	private static final String[] PLAIN_ARRAY = {"14057","26607","A7024","63113","63095","62722","49821","49744","49213","45978","45698","45108","45033"};
	private static final String[] ENCRYPTED_ARRAY = {"qheHnMstbqLoLHptNRpskw==","D5es64/5bJrx1X4yANzdZA==","Y3ge1s2BPeWrEreflyO7hg==","iqffMkR1qeBwq6qKcKLycg==",
		"cv1fzlqvutRaHi/CtDJ/lA==","LC8He5J+dM2kOZveGdXMGg==","+lrMC+WVGPf8w/5QRTWpTA==","3n6OM5TMZ5p9NL1QaVrmzA==",
		"U/+qQUP0nxOoVtLY47Q5Mw==","6+FTNiImOa62UOCoFXMiig==","f9F7vCmVjQbL1Jj2lx1V3Q==","jPm7OP4Cwn7drctuslVi8Q==","jMAKk/IQOL7CZGgWEldYYg=="};
	private static final String PASSWORD = "SingleSignOnCryp";
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CryptoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CryptoTest.class );
    }

    /**
     * Test encryption
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws NoSuchPaddingException 
     * @throws UnsupportedEncodingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public void testCrypt() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
    	Crypto crypto = new Crypto(PASSWORD);

    	for (int i = 0; i < PLAIN_ARRAY.length; i++) {
			String output = crypto.encrypt(PLAIN_ARRAY[i]);
			System.out.println("original:" + PLAIN_ARRAY[i]);
			System.out.println("encrypted: " + output);
			System.out.println("expected : " + ENCRYPTED_ARRAY[i]);
			System.out.println("-------------------------------");
			assertEquals(ENCRYPTED_ARRAY[i], output);
		}
    }
    
    /**
     * Test decryption
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws NoSuchPaddingException 
     * @throws UnsupportedEncodingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public void testDecrypt() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
    	Crypto crypto = new Crypto(PASSWORD);

		for (int i = 0; i < ENCRYPTED_ARRAY.length; i++) {
			String output = crypto.decrypt(ENCRYPTED_ARRAY[i]);
			System.out.println("original:" + ENCRYPTED_ARRAY[i]);
			System.out.println("decrypted: " + output);
			System.out.println("expected : " + PLAIN_ARRAY[i]);
			System.out.println("-------------------------------");
			assertEquals(PLAIN_ARRAY[i], output);
		}
    }
}
