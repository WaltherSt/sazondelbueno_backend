package sazondelbueno.web.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sazondelbueno.web.Model.Account;
import sazondelbueno.web.Repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;


    @Override
    public Account saveAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public List<Account> listAccounts() {
        return repository.findAll();
    }

    @Override
    public Optional<Account> existAccount(String email) {
        return repository.findByUsername(email);
    }

    @Override
    public Optional<Account> getById(Long id) {
        return repository.findById(id);
    }


    @Override
    @Transactional
    public boolean deleteById(Long id) {
        try {
            Optional<Account> optionalUser = repository.findById(id);
            if (optionalUser.isPresent()) {
                Account user = optionalUser.get();
                user.setActive(false);
                repository.save(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean restore(Long id) {
        try {
            System.out.println(id);
            Optional<Account> optionalUser = repository.findById(id);
            if (optionalUser.isPresent()) {

                Account user = optionalUser.get();
                user.setActive(true);
                repository.save(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Account updateById(Long id, Account account) {
        Account c = repository.getReferenceById(id);
        BeanUtils.copyProperties(account, c, "id");
        return repository.save(c);
    }
}
