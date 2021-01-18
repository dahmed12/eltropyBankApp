package bank.eltropy.repository;

import org.apache.catalina.realm.JNDIRealm.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.eltropy.domain.Account;
import bank.eltropy.domain.AccountType;
import bank.eltropy.domain.CreateAccountClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class CreateAccountPostgresClient implements CreateAccountClient {
	private final AccountRepository accountRepository;
	private final UserRepository userRepository;

	@Override
	public void create(Account account) {
		accountRepository.save(account);
	}

	@Override
	@Transactional
	public void create(long userId, AccountType accountType) {
		User user = userRepository.getOne(userId);
		Account account = Account.generateAccount(user, accountType);
		user.addAccount(account);
		accountRepository.save(account);
	}

}
