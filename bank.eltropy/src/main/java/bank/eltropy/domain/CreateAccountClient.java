package bank.eltropy.domain;

import java.util.Optional;

public interface CreateAccountClient {
	void create(Account account);

	void create(long userId, AccountType accountType);
}