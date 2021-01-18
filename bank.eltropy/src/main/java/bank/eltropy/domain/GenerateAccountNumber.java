package bank.eltropy.domain;

public class GenerateAccountNumber {

	private static final String BANK_CODE = "111";

	public static String generate() {
		Iban iban = new Iban.Builder().countryCode(CountryCode.TX).bankCode(BANK_CODE).buildRandom();
		return iban.toString();
	}
}
