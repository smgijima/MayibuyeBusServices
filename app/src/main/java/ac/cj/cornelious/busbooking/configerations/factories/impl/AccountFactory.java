package ac.cj.cornelious.busbooking.configerations.factories.impl;


import ac.example.mgijima.bustransportingsystem.domain.Account;

/**
 * Created by Cornelious on 5/12/2016.
 */
public class AccountFactory {
    private static AccountFactory accountFactory = null;

    public static AccountFactory getInstance() {
        if (accountFactory == null)
            accountFactory = new AccountFactory();
        return accountFactory;
    }

    public Account createAccount(Long acc,String username, String password) {
        Account activateAccount = new Account.AccountBuilder()
                .id(acc)
                .username(username)
                .password(password)

                .build();
        return activateAccount;

    }
}
