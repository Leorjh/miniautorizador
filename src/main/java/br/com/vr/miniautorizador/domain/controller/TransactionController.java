package br.com.vr.miniautorizador.domain.controller;

import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.enums.StatusTransactionCard;
import br.com.vr.miniautorizador.domain.exception.CardException;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.net.URI;

@RestController
@RequestMapping("/transacoes")
public class TransactionController {

	@Autowired
	private CardServiceImpl cardService;

	@Autowired
	private TransactionServiceImpl transactionService;

	@Autowired
	private CardRepository cardRepository;

	@PostMapping()
	public ResponseEntity<Object> makeTransaction(@RequestBody TransactionDTO transactionDTO) {
		try {
			Optional<Card> card = cardRepository.findByCardNumber(transactionDTO.getCardNumber());
			if (!card.isPresent()) {
				throw new CardException(StatusTransactionCard.CARTAO_INEXISTENTE + " card number: " + transactionDTO.getCardNumber());
			}

			if (!card.get().getPasswordCard().equals(transactionDTO.getPasswordCard())) {
				throw new CardException(StatusTransactionCard.SENHA_INVALIDA + " card number: " + transactionDTO.getCardNumber());
			}

			if (card.get().getBalance().compareTo(transactionDTO.getValue()) < 0) {
				throw new CardException(StatusTransactionCard.SALDO_INSUFICIENTE + " card number: " + transactionDTO.getCardNumber());
			}

//			// atualizar o saldo do cartão e salvar a transação
			card.get().setBalance(card.get().getBalance().subtract(transactionDTO.getValue()));
			cardService.save(card.get());
//
//			// retornar a resposta com o código 201 Created e o corpo da resposta com o objeto da transação
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(card.get().getId()).toUri();
			return ResponseEntity.created(location).body(transactionDTO);

		} catch (CardException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}


}
