package bank.eltropy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bank.eltropy.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findAccountByAccountNumber(String accountNumber);

}
