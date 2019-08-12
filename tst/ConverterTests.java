import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class ConverterTests {
	
	private ConvertNumbersToWords converter;
	
	@Test
	public void testNull() {
		converter = new ConvertNumbersToWords();
		String s = null;
		assertThrows(IllegalArgumentException.class, () -> {
			converter.ConvertNumbersToWords(s);
		});
	}
	
	@Test
	public void testEmpty() {
		converter = new ConvertNumbersToWords();
		String s = "";
		assertThrows(IllegalArgumentException.class, () -> {
			converter.ConvertNumbersToWords(s);
		});
	}
	
	@Test
	public void testTwoLeadingZeros() {
		converter = new ConvertNumbersToWords();
		String s = "00.05";
		assertThrows(IllegalArgumentException.class, () -> {
			converter.ConvertNumbersToWords(s);
		});
	}
	
	@Test
	public void testManyLeadingZeros() {
		converter = new ConvertNumbersToWords();
		String s = "00002351.05";
		assertThrows(IllegalArgumentException.class, () -> {
			converter.ConvertNumbersToWords(s);
		});
	}
	
	@Test
	public void testOneLeadingZero() {
		converter = new ConvertNumbersToWords();
		String s = "01.05";
		assertThrows(IllegalArgumentException.class, () -> {
			converter.ConvertNumbersToWords(s);
		});
	}
	
	@Test
	public void testProvidedSampleInput() {
		converter = new ConvertNumbersToWords();
		String s = "123.45";
		assertEquals("ONE HUNDRED AND TWENTY-THREE DOLLARS AND FORTY-FIVE CENTS", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testZeroDollars() {
		converter = new ConvertNumbersToWords();
		String s = "0.05";
		assertEquals("ZERO DOLLARS AND FIVE CENTS", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testZeroCents() {
		converter = new ConvertNumbersToWords();
		String s = "2.00";
		assertEquals("TWO DOLLARS AND ZERO CENTS", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testOneDollar() {
		converter = new ConvertNumbersToWords();
		String s = "1.02";
		assertEquals("ONE DOLLAR AND TWO CENTS", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testOneCent() {
		converter = new ConvertNumbersToWords();
		String s = "9.01";
		assertEquals("NINE DOLLARS AND ONE CENT", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testThousands() {
		converter = new ConvertNumbersToWords();
		String s = "2000.13";
		assertEquals("TWO THOUSAND DOLLARS AND THIRTEEN CENTS", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testMillions() {
		converter = new ConvertNumbersToWords();
		String s = "123000000.00";
		assertEquals("ONE HUNDRED TWENTY-THREE MILLION DOLLARS AND ZERO CENTS", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testMillionsThousandsHundredsTensOnesCents() {
		converter = new ConvertNumbersToWords();
		String s = "13045970.69";
		assertEquals("THIRTEEN MILLION FORTY-FIVE THOUSAND NINE HUNDRED AND SEVENTY DOLLARS AND SIXTY-NINE CENTS", converter.ConvertNumbersToWords(s));
	}
	
	@Test
	public void testAndBeforeOnesWithoutHundredsInDollars() {
		converter = new ConvertNumbersToWords();
		String s = "2009.00";
		assertEquals("TWO THOUSAND AND NINE DOLLARS AND ZERO CENTS", converter.ConvertNumbersToWords(s));
	}
}
