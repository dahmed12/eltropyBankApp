package bank.eltropy.repository;
import java.util.Optional;

import org.springframework.stereotype.Service;

import bank.eltropy.domain.Account;
import bank.eltropy.domain.RetreiveAccount;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AccountRetrievalPostgresClient implements RetreiveAccount {

    public final AccountRepository accountRepository;

    @Override
    public Optional<Account> findById(Long accountId) {
       return accountRepository.findById(accountId);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }
}
