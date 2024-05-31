package sazondelbueno.web.Service;

import sazondelbueno.web.Model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account saveAccount(Account account);

    List<Account> listAccounts();
    boolean restore(Long id);

    Optional<Account> existAccount(String email);

    Optional<Account> getById(Long id);

    boolean deleteById(Long id);

    Account updateById(Long id, Account account);
}
