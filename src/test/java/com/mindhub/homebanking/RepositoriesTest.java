//package com.mindhub.homebanking;
//
//import com.mindhub.homebanking.models.*;
//import com.mindhub.homebanking.repositories.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
//
//import java.util.List;
//import java.util.Set;
//
//@DataJpaTest
//
//@AutoConfigureTestDatabase(replace = NONE)
//
//public class RepositoriesTest {
//
//
//
//    @Autowired
//
//    LoanRepository loanRepository;
//
//
//    @Autowired
//    ClientRepository clientRepository;
//
//
//    @Autowired
//    AccountRespository accountRespository;
//
//    @Autowired
//    CardRepository cardRepository;
//
//    @Autowired
//    TransactionRepository transactionRepository;
//
//
//
//    @MockBean
//    PasswordEncoder passwordEncoder;
//
//
//    @Test
//
//    public void existLoans(){
//
//        List<Loan> loans = loanRepository.findAll();
//
//        assertThat(loans,is(not(empty())));
//
//    }
//
//
//
//    @Test
//
//    public void existPersonalLoan(){
//
//        List<Loan> loans = loanRepository.findAll();
//
//        assertThat(loans, hasItem(hasProperty("name", is("personal"))));
//
//    }
//
//    @Test
//    public void existClients(){
//
//        List<Client> clients = clientRepository.findAll();
//        assertThat(clients, is(not(empty())));
//
//    }
//
//
//    @Test
//    public void existAdmin(){
//
//        List<Client> clients = clientRepository.findAll();
//        assertThat(clients, hasItem(hasProperty("email", is("admin@mindhub.com"))));
//
//    }
//
//
//    @Test
//    public void existAccounts(){
//        List<Account> accounts = accountRespository.findAll();
//        assertThat(accounts, is(not(empty())));
//
//    }
//
//
//    @Test
//    public void existAccountVIN001(){
//        List<Account> accounts = accountRespository.findAll();
//        assertThat(accounts, hasItem(hasProperty("number", is("VIN001"))));
//    }
//    @Test
//    public void existAccountNumber(){
//        List<Account> accounts = accountRespository.findAll();
//        assertThat(accounts, hasItem(hasProperty("number", is(not(empty())))));
//    }
//
//
//    @Test
//    public void existCard(){
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards, is(not(empty())));
//
//    }
//    @Test
//    public void existCardMelbaMorel(){
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards, hasItem(hasProperty("cardHolder", is("Melba Morel"))));
//
//    }
//
//    @Test
//    public void existCardMatias(){
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards, hasItem(hasProperty("cardHolder", is("matias gonzalez"))));
//
//    }
//
//
//    @Test
//    public void existCardGold(){
//
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards, hasItem(hasProperty("cardColor", is(CardColor.GOLD))));
//
//    }
//
//    @Test
//    public void existCardSilver(){
//
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards, hasItem(hasProperty("cardColor", is(CardColor.SILVER))));
//
//    }
//    @Test
//    public void existCardTitanium(){
//
//        List<Card> cards = cardRepository.findAll();
//        assertThat(cards, hasItem(hasProperty("cardColor", is(CardColor.TITANIUM))));
//
//    }
//
//
//    @Test
//    public void existTransaction(){
//        List<Transaction> transactions = transactionRepository.findAll();
//        assertThat(transactions, is(not(empty())));
//    }
//
//
//    @Test
//    public void existTransactionNetflix(){
//        List<Transaction> transactions = transactionRepository.findAll();
//        assertThat(transactions, hasItem(hasProperty("description", is("Netflix"))));
//    }
//
//    @Test
//    public void existTransactionSpotify(){
//        List<Transaction> transactions = transactionRepository.findAll();
//        assertThat(transactions, hasItem(hasProperty("description", is("Spotify"))));
//    }
//
//
//
//
//
//}
