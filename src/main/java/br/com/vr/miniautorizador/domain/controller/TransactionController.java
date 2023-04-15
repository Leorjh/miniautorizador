package br.com.vr.miniautorizador.domain.controller;

import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.exception.IncorrectPasswordException;
import br.com.vr.miniautorizador.domain.exception.NotFoundException;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.impl.CardServiceImpl;
import br.com.vr.miniautorizador.domain.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import br.com.vr.miniautorizador.domain.dto.TransactionDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {

	@Autowired
	private CardServiceImpl cardService;

	@Autowired
	private TransactionServiceImpl transactionService;

	@Autowired
	private CardRepository cardRepository;

//	@PostMapping("/transacoes")
//	public ResponseEntity<Object> makeTransaction(@RequestBody TransactionDTO transactionDTO){
//		try {
//			TransactionDTO transactional = transactionService.makeTransactional(transactionDTO);
//			return ResponseEntity.ok().body(transactional);
//		} catch (NotFoundException e) {
//			return ResponseEntity.unprocessableEntity().body(e.getMessage());
//		}
//	}

	@PostMapping("/transacoes")
	public ResponseEntity<Object> makeTransaction(@RequestBody TransactionDTO transactionDTO) {
		try {
			Optional<Card> card = cardRepository.findByCardNumber(transactionDTO.getCardNumber());
			if (!card.isPresent()) {
				throw new NotFoundException("Card not found for card number: " + transactionDTO.getCardNumber());
			}

			// validar se a senha está correta
			if (!card.get().getPasswordCard().equals(transactionDTO.getPasswordCard())) {
				new IncorrectPasswordException("Senha incorreta para o cartão: " + transactionDTO.getCardNumber());
				return ResponseEntity.unprocessableEntity().body("SENHA_INVALIDA");
			}

			// validar se o saldo é suficiente para a transação
			if (card.get().getBalance().compareTo(transactionDTO.getValue()) < 0) {
				return ResponseEntity.unprocessableEntity().body("SALDO_INSUFICIENTE");
			}

			return ResponseEntity.ok().body(transactionDTO);

//			// atualizar o saldo do cartão e salvar a transação
//			card(card.getBalance().subtract(transacao.getValor()));
//			cardService.save(card);
//			transacaoService.save(new Transacao(card, transacao.getValor()));
//
//			// retornar a resposta com o código 201 Created e o corpo da resposta com o objeto da transação
//			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(transacao.getId()).toUri();
//			return ResponseEntity.created(location).body(transacao);

		} catch (NotFoundException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}


}
