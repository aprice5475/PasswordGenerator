package sys;

import java.util.Random;

/**
 * Generates a strong password filled with upper and lower letters, numbers, and
 * special characters.
 *
 * Provided below are some constants to aid in password generation process.
 * These are recommended values, but you can experiment with others.
 *
 * @author apricejr
 *
 */
public class Main {
	public static final String SPECIAL_CHARS = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
	public static final int MINIMUM_LENGTH = 12; //strong passwords are at least 12 characters long
	public static final int NO_OF_SPECIAL_CHARS = 3; //there should be at least 3
	private static Random random = new Random(); //only one instance of this class is neede

	public static void main(String[] args) {
		int length = MINIMUM_LENGTH; //
		String password = generatePassword(length, NO_OF_SPECIAL_CHARS, random);
		System.out.println(password);
	}

	private static String generatePassword(int length, int numOfSpecial, Random r) {
		StringBuffer str = new StringBuffer();
		boolean correctionBlock = false;

		while (length >= 0) {
			char toAppend;
			boolean isNumber = r.nextInt(Math.abs(numOfSpecial) + 2) == 1, isUpper = r.nextBoolean();
			int lower = 0, upper = 0, num = 0, special = 0;
			if (length >= 2) {//where most randomizations happen
				if ((numOfSpecial > 0) && (r.nextBoolean())) {
					toAppend = SPECIAL_CHARS.charAt(r.nextInt(SPECIAL_CHARS.length()));
					numOfSpecial--;
					special++;
				} else if (isNumber) {
					toAppend = Integer.toString(r.nextInt(10)).charAt(0);
					num++;
				} else if (isUpper) {
					toAppend = (char) (r.nextInt(25) + 65);
					upper++;
				} else {
					toAppend = (char) (r.nextInt(25) + 97);
					lower++;
				}
			} else if ((length < 2) && !correctionBlock) { //balances string
				int min = Math.min(Math.min(lower, upper), Math.min(special, num));
				if (min == lower) {
					toAppend = (char) (r.nextInt(25) + 97);
					lower++;
				} else if (min == upper) {
					toAppend = (char) (r.nextInt(25) + 65);
					upper++;
				} else if (min == special) {
					toAppend = SPECIAL_CHARS.charAt(r.nextInt(SPECIAL_CHARS.length()));
					numOfSpecial--;
					special++;
				} else {
					toAppend = Integer.toString(r.nextInt(10)).charAt(0);
					num++;
				}
				correctionBlock = true;
			} else {
				toAppend = (char) (r.nextInt(50) + 65);
			}
			str.append(toAppend);
			length--;
		}
		return str.toString();
	}

	private static boolean validPassword(String password, int numOfSpecial) {
		for (char c : password.toCharArray()) {
			if (SPECIAL_CHARS.indexOf(c) != -1) {
				numOfSpecial--;
			}
		}
		return numOfSpecial == 0;
	}

}
