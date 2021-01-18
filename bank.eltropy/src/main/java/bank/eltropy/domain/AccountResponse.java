package bank.eltropy.domain;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;

@Builder
@Value
public class AccountResponse {

    long id;
    long userID;
    String accountNumber;
    BigDecimal amount;
    BigDecimal fee;
    AccountType accountType;
}