package bank.eltropy.domain;

import java.util.Optional;

public interface RetreiveAccount {
	 public Optional<Account> findById(Long accountId);

	    public Optional<Account> findByAccountNumber(String accountNumber);
	}