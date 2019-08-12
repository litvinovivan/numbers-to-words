import java.util.HashMap;

/**
 * @author Ivan Litvinov
 * Class to convert number representing dollars and cents into words.
 * 
 * example of use: 
 * 		Input: “123.45”
 *		Output: “ONE HUNDRED AND TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS”
 */
public class ConvertNumbersToWords {
	
	HashMap<String, String> NUM_TO_WORD_MAP = new HashMap<String, String>();
	
	public ConvertNumbersToWords() {
		NUM_TO_WORD_MAP.put("1", "ONE ");
		NUM_TO_WORD_MAP.put("2", "TWO ");
		NUM_TO_WORD_MAP.put("3", "THREE ");
		NUM_TO_WORD_MAP.put("4", "FOUR ");
		NUM_TO_WORD_MAP.put("5", "FIVE ");
		NUM_TO_WORD_MAP.put("6", "SIX ");
		NUM_TO_WORD_MAP.put("7", "SEVEN ");
		NUM_TO_WORD_MAP.put("8", "EIGHT ");
		NUM_TO_WORD_MAP.put("9", "NINE ");
		NUM_TO_WORD_MAP.put("10", "TEN ");
		NUM_TO_WORD_MAP.put("11", "ELEVEN ");
		NUM_TO_WORD_MAP.put("12", "TWELVE ");
		NUM_TO_WORD_MAP.put("13", "THIRTEEN ");
		NUM_TO_WORD_MAP.put("14", "FOURTEEN ");
		NUM_TO_WORD_MAP.put("15", "FIFTEEN ");
		NUM_TO_WORD_MAP.put("16", "SIXTEEN ");
		NUM_TO_WORD_MAP.put("17", "SEVENTEEN ");
		NUM_TO_WORD_MAP.put("18", "EIGHTEEN ");
		NUM_TO_WORD_MAP.put("19", "NINETEEN ");
		NUM_TO_WORD_MAP.put("20", "TWENTY ");
		NUM_TO_WORD_MAP.put("30", "THIRTY ");
		NUM_TO_WORD_MAP.put("40", "FORTY ");
		NUM_TO_WORD_MAP.put("50", "FIFTY ");
		NUM_TO_WORD_MAP.put("60", "SIXTY ");
		NUM_TO_WORD_MAP.put("70", "SEVENTY ");
		NUM_TO_WORD_MAP.put("80", "EIGHTY ");
		NUM_TO_WORD_MAP.put("90", "NINETY ");
	}
	
	/**
	 * Helper method to convert number between 000 to 999 to words.
	 * 
	 * @param number Number to convert to words, digits 0-9, length 1-3, cannot be empty or null.
	 * @param and_flag Indicates if word AND is to be inserted before numbers 1-99. 
	 * 		Should be True for numbers below 1000 only in the NumberString.
	 * @return words representing number or empty string if number is 000. 
	 */
	private String hundredsTensOnesToWords(String number, Boolean and_flag) {
		String hundreds = "";
		String tens = "";
		String ones = "";
		String result = "";
		Boolean ignoreOnes = false;
		int length = number.length();
		if (length == 3) {
			hundreds = number.substring(0, 1);
			tens = number.substring(1, 2);
		} else if (length == 2) {
			tens = number.substring(0, 1);
		}
		ones = number.substring(length - 1, length);
		
		if (!hundreds.isEmpty() && !hundreds.equals("0")) {
			result += this.NUM_TO_WORD_MAP.get(hundreds) + "HUNDRED ";
			if ((!tens.equals("0") || !ones.equals("0")) && and_flag){
				result += "AND ";
			}
		}
		if (!tens.isEmpty()) {
			if (tens.equals("1")) {
				ignoreOnes = true;
				result += this.NUM_TO_WORD_MAP.get(tens + ones);  //gets 10-19
			} else if (!tens.equals("0")) { 
				result += this.NUM_TO_WORD_MAP.get(tens + "0");	//gets 20, 30, etc...
				if (!ones.equals("0")) {
					result = result.trim() + "-";	//dash after tens -
				}
			}
		}
		if (!ignoreOnes && !ones.equals("0")) {
			result += this.NUM_TO_WORD_MAP.get(ones);
		}
		return result;
	}
	
	/**
	 * Main driver method to convert number to words
	 * 
	 * @param NumberString Number to convert to words, cannot be null and must be in the correct
	 *  format as follows: 
	 * 	    1 to 9 digits (first digit cannot be 0 if 2 digits or more in that part) followed by
	 * 		full stop followed by 2 digits.
	 * @return Word representation of the NumberString number
	 * @throws IllegalArgumentException if NumberString is null or an empty string or in the wrong format.
	 */
	public String ConvertNumbersToWords(String NumberString) {
		String result = "";
		if (NumberString == null || !NumberString.matches("^([1-9]\\d{0,7})?\\d\\.\\d{2}$")) {
			throw new IllegalArgumentException("NumberString cannot be null and must be in the right format");
		} 
		String[] inputArray = NumberString.split("\\.");
		String dollars = inputArray[0];
		String cents = inputArray[1];
		String millions = "";
		String thousands = "";
		String hundredsAndBelow = "";
		int length = dollars.length();
		
		// break up dollars into millions, thousands and hundredsAndBelow
		for (int i = length - 1; i >= 0; i-- ) {
			if (i >= length - 3) {
				hundredsAndBelow = dollars.charAt(i) + hundredsAndBelow;
			} else if (i >= length - 6) {
				thousands = dollars.charAt(i) + thousands;
			} else {
				millions = dollars.charAt(i) + millions;
			} 
		}
		if (!millions.isEmpty()) {
			millions = this.hundredsTensOnesToWords(millions, false);
			if (!millions.isEmpty()) {
				result = millions + "MILLION ";
			}
		}
		if (!thousands.isEmpty()) {
			thousands = this.hundredsTensOnesToWords(thousands, false);
			if (!thousands.isEmpty()) {
				result = result + thousands + "THOUSAND ";
			}
		}
		String and = "";
		if (hundredsAndBelow.length() == 3 && hundredsAndBelow.charAt(0) == '0') {
			and = "AND ";
		}
		hundredsAndBelow = this.hundredsTensOnesToWords(hundredsAndBelow, true);
		if (!hundredsAndBelow.isEmpty()) {
			result = result + and + hundredsAndBelow;
		}
		if (!result.isEmpty()) {
			if (result.equals("ONE ")) {
				result += "DOLLAR AND ";
			} else {
				result += "DOLLARS AND ";
			}
		} else {
			result = "ZERO DOLLARS AND ";
		}
		cents = this.hundredsTensOnesToWords(cents, true);
		if (!cents.isEmpty()) {
			if (cents.equals("ONE ")) {
				result = result + cents + "CENT";
			} else {
				result = result + cents + "CENTS";
			}
		} else {
			result += "ZERO CENTS";
		}
		return result;
	}
}
