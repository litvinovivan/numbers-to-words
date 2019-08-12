import java.util.Scanner;

/**
 * Class to test the ConvertNumbersTowords API in console
 * 
 * @author Ivan Litvinov 
 */
public class ConsoleTest {
	public static void main(String[] args) {
		
		ConvertNumbersToWords converter = new ConvertNumbersToWords();
		Scanner in = new Scanner(System.in);
	    String s;
	    System.out.println("\nEnter the amount in dollars and cents in the following format 'x.xx' without quotes, commas or dollar sign. Dollar value can be in the rage 0 to 999,999,999. Cents value can be in range 00 to 99");
	    while (true) {
	    	System.out.println("Enter the amount in dollars and cents:");
		    s = in.nextLine();
		    if (s.matches("^([1-9]\\d{0,7})?\\d\\.\\d{2}$")) {
		    	System.out.println("Conversion to words is: " + converter.ConvertNumbersToWords(s) + "\n");
		    } else {
		    	System.out.println("Invalid format. Please try again");
		    }
	    }
	}
}