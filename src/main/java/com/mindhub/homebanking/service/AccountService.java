package com.mindhub.homebanking.service;

import com.mindhub.homebanking.DTOS.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {


    public List<AccountDTO> getAccounts();

    public AccountDTO getAccount(Long id);

    public void saveAccount(Account account);

    Account findByNumber(String number);


}
