package br.com.vr.miniautorizador.domain.service;

import br.com.vr.miniautorizador.domain.dto.TransactionDTO;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

	TransactionDTO makeTransactional(TransactionDTO transactionDTO);

}
