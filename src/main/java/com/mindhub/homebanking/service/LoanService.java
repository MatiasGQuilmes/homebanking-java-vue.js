package com.mindhub.homebanking.service;

import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.DTOS.LoanApplicationDTO;
import com.mindhub.homebanking.DTOS.LoanDTO;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {

    public List<LoanDTO> getLoansDTO();

    public Loan getLoan(Long id);

}
