package com.mindhub.homebanking.service.imprement;

import com.mindhub.homebanking.DTOS.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRespository;
import com.mindhub.homebanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpletement implements AccountService {

    @Autowired
    AccountRespository accountRespository;

    @Override
    public List<AccountDTO> getAccounts() {
        return accountRespository.findAll().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccount(Long id) {
        return accountRespository.findById(id).map(idAccount -> new AccountDTO(idAccount)).orElse(null);
    }

    @Override
    public void saveAccount(Account account) {
        accountRespository.save(account);
    }

    @Override
    public Account findByNumber(String number) {
        return accountRespository.findByNumber(number);
    }


}
