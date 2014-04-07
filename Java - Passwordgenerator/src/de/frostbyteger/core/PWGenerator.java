package de.frostbyteger.core;
import java.util.Random;

/**
 * 
 */

/**
 * @author Kevin
 * @version 1.00
 * TODO: Implement utf8 and unicode charsets
 * This class is responsible for the password generation.
 * It is capable of creating passwords with the ASCII charset,
 * the UTF-8 charset and the unicode charset.
 */
public class PWGenerator {
	
	private Random rndm;
	
	private int asciiStart = 33;
	private int asciiEnd = 126;
	
	private int utf8Start = 21;
	private int utf8End = 0;
	
	private int unicodeStart = 21;
	private int unicodeEnd = 0;
	
	
	public PWGenerator(){
		rndm = new Random();
	}
	
	
	public String generatePW(int state, int length){
		int start = 0;
		int end = 0;
		switch(state){
		case 1:
			start = asciiStart;
			end = asciiEnd;
		case 2:
			start = utf8Start;
			end = utf8End;
		case 3:
			start = unicodeStart;
			end = unicodeEnd;
		}
		
		String pw = "";
		int buffer = 0;
		for(int i = 0; i < length; i++){
			buffer = rndm.nextInt(end);
			while(buffer < start){
				buffer = rndm.nextInt(end);
			}
			pw += (char)buffer;
		}
		return pw;
	}
	
	public String generatePW(int state, int length, char[] dontUse){
		int start = 0;
		int end = 0;
		switch(state){
		case 1:
			start = asciiStart;
			end = asciiEnd;
			break;
		case 2:
			start = utf8Start;
			end = utf8End;
			break;
		case 3:
			start = unicodeStart;
			end = unicodeEnd;
			break;
		}
		
		String pw = "";
		int buffer = 0;
		for(int i = 0; i < length; i++){
			buffer = rndm.nextInt(end);
			while(buffer < start){
				buffer = rndm.nextInt(end);
			}
			boolean exit = false;
			for(int j = 0; j < dontUse.length; j++){
				if(buffer == (int)dontUse[j]){
					i--;
					exit = true;
					break;
				}
			}
			if(!exit){
				pw += (char)buffer;
			}
			
		}
		return pw;
	}
	
	public String generatePW(int length, char[] use){
		String pw = "";
		int buffer = 0;
		for(int i = 0; i < length; i++){
			if(use.length != 0){
				buffer = rndm.nextInt(use.length);
				pw += use[buffer];
			}else{
				buffer = rndm.nextInt(asciiEnd);
				while(buffer < asciiStart){
					buffer = rndm.nextInt(asciiEnd);
				}
				pw += (char)buffer;
			}
			
		}
		return pw;
	}


	/**
	 * @return the rndm
	 */
	public Random getRndm() {
		return rndm;
	}


	/**
	 * @param rndm the rndm to set
	 */
	public void setRndm(Random rndm) {
		this.rndm = rndm;
	}


	/**
	 * @return the asciiStart
	 */
	public int getAsciiStart() {
		return asciiStart;
	}


	/**
	 * @param asciiStart the asciiStart to set
	 */
	public void setAsciiStart(int asciiStart) {
		if(asciiStart >= 0){
			this.asciiStart = asciiStart;
		}else{
			throw new NegativeNumberException("The charset start must be 0 or above!");
		}
		
	}


	/**
	 * @return the asciiEnd
	 */
	public int getAsciiEnd() {
		return asciiEnd;
	}


	/**
	 * @param asciiEnd the asciiEnd to set
	 */
	public void setAsciiEnd(int asciiEnd) {
		if(asciiEnd >= 0){
			if(asciiEnd > this.asciiStart){
				this.asciiEnd = asciiEnd;
			}else{
				throw new EqualOrLessException("The charset end must greater than the charset start!");
			}
		}else{
			throw new NegativeNumberException("The charset start must be 0 or above!");
		}
		
	}


	/**
	 * @return the utf8Start
	 */
	public int getUtf8Start() {
		return utf8Start;
	}


	/**
	 * @param utf8Start the utf8Start to set
	 */
	public void setUtf8Start(int utf8Start) {
		if(utf8Start >= 0){
			this.utf8Start = utf8Start;
		}else{
			throw new NegativeNumberException("The charset start must be 0 or above!");
		}
	}


	/**
	 * @return the utf8End
	 */
	public int getUtf8End() {
		return utf8End;
	}


	/**
	 * @param utf8End the utf8End to set
	 */
	public void setUtf8End(int utf8End) {
		
		if(utf8End >= 0){
			if(utf8End > this.utf8Start){
				this.utf8End = utf8End;
			}else{
				throw new EqualOrLessException("The charset end must greater than the charset start!");
			}
		}else{
			throw new NegativeNumberException("The charset start must be 0 or above!");
		}
	}


	/**
	 * @return the unicodeStart
	 */
	public int getUnicodeStart() {
		return unicodeStart;
	}


	/**
	 * @param unicodeStart the unicodeStart to set
	 */
	public void setUnicodeStart(int unicodeStart) {
		if(unicodeStart >= 0){
			this.unicodeStart = unicodeStart;
		}else{
			throw new NegativeNumberException("The charset start must be 0 or above!");
		}
	}


	/**
	 * @return the unicodeEnd
	 */
	public int getUnicodeEnd() {
		return unicodeEnd;
	}


	/**
	 * @param unicodeEnd the unicodeEnd to set
	 */
	public void setUnicodeEnd(int unicodeEnd) {
		
		if(unicodeEnd >= 0){
			if(unicodeEnd > this.unicodeStart){
				this.unicodeEnd = unicodeEnd;
			}else{
				throw new EqualOrLessException("The charset end must greater than the charset start!");
			}
		}else{
			throw new NegativeNumberException("The charset start must be 0 or above!");
		}
	}

}
