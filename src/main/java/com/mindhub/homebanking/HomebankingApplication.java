package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mindhub.homebanking.models.CardColor.SILVER;
import static com.mindhub.homebanking.models.TransactionType.*;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);



	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRespository accountRespository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){	//genera datos iniciales


		return args -> {



			Client client1 = new Client("matias", "gonzalez", "mati@gmail.com", passwordEncoder.encode("mati123"));
			Client client2 = new Client("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("melba123"));
			Client admin = new Client("ADMIN", "ADMIN", "admin@mindhub.com", passwordEncoder.encode("admin123"));


			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(admin);

			Account account1 = new Account("VIN001", LocalDateTime.now(), 5000.0, client2);
			Account account2 = new Account("VIN002", LocalDateTime.now(), 8000.5, client2);
			Account account3 = new Account("VIN003", LocalDateTime.now(), 10000, client1);


			accountRespository.save(account1);
			accountRespository.save(account2);
			accountRespository.save(account3);


			Transaction transaction1 = new Transaction(DEBIT, -150, "Spotify", LocalDateTime.now(), account1);
			Transaction transaction2 = new Transaction(CREDIT, 3000, "Netflix", LocalDateTime.now(), account1);
			Transaction transaction3 = new Transaction(CREDIT, 2000, "HBO MAX", LocalDateTime.now(), account1);

			Transaction transaction4 = new Transaction(DEBIT, -6000, "shopping", LocalDateTime.now(), account2);
			Transaction transaction5 = new Transaction(CREDIT, 6000, "Socio Boca Jrs", LocalDateTime.now(), account2);
			Transaction transaction6 = new Transaction(DEBIT, -4000, "supermarket", LocalDateTime.now(), account2);

			Transaction transaction7 = new Transaction(CREDIT, 10000, "Departamento", LocalDateTime.now(), account3);


			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);
			transactionRepository.save(transaction7);


			Loan loan1 = new Loan("mortgage", 500000, List.of(12,24,36,48,60));
			Loan loan2 = new Loan("personal", 100000, List.of(6,12,24));
			Loan loan3 = new Loan("automotive", 300000, List.of(6,12,24,36));

			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);


			ClientLoan clientLoan1 = new ClientLoan(60,400000.0,client2, loan1);
			ClientLoan clientLoan2 = new ClientLoan(40, 200000.0,client2, loan2);
			ClientLoan clientLoan3 = new ClientLoan(20, 500000.0, client1, loan3);

			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);


			Card card1 = new Card(CardColor.TITANIUM, CardType.CREDIT, "4412 3145 1231 5123", 221, LocalDate.now(),LocalDate.now().plusYears(5), client2);
			Card card2 = new Card(CardColor.GOLD, CardType.DEBIT, "4212 1231 4212 3122", 551, LocalDate.now(),LocalDate.now().plusYears(5), client2);
			Card card3 = new Card(CardColor.SILVER, CardType.CREDIT, "1314 1521 4632 6421", 142, LocalDate.now(),LocalDate.now().plusYears(5), client1);
			Card card4 = new Card(CardColor.SILVER, CardType.CREDIT, "4412 1341 6441 3452", 667, LocalDate.now(),LocalDate.now().plusYears(5), client2);

			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);
			cardRepository.save(card4);

		};
	}

}
