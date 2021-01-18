package bank.eltropy.exception;

import org.springframework.stereotype.Service;

import bank.eltropy.domain.Account;
import bank.eltropy.domain.AccountType;
import bank.eltropy.domain.CreateAccountClient;
import bank.eltropy.domain.GenerateAccountNumber;
@Service
@RequiredArgsConstructor
public class AccountFacade {

	private final CreateAccountClient createAccountClient;

	public void create(Account account) {
		createAccountClient.create(account);
	}

	public static String generateNewAccountNumber() {
		return generateNewAccountNumber();
	}

	public void createPremium(Long userId) {
		createAccountClient.create(userId, AccountType.CHECKING);
	}

	public void createStandard(Long userId) {
		createAccountClient.create(userId, AccountType.SAVING);
	}
}