package com.skax.eatool.reference.business.facade;

import java.math.BigDecimal;

public interface IAccountManagement {

    BigDecimal getAccountBalance(String accountNumber);

    void updateAccountBalance(String accountNumber, BigDecimal newBalance);

    boolean validateAccount(String accountNumber, String bankCode);

    void createAccount(String accountNumber, String accountType);

    void deleteAccount(String accountNumber);

}
