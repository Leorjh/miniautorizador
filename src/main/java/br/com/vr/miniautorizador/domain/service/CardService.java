package br.com.vr.miniautorizador.domain.service;

import br.com.vr.miniautorizador.domain.dto.CardDTO;
import br.com.vr.miniautorizador.domain.entity.Card;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CardService {

	Card save(Card card);

	boolean validationCardAlreadyExists(String cardNumber);

	CardDTO getCard(Integer cardId);

	List<CardDTO> getAllCards();

	BigDecimal getCardBalance(String cardNumber);

	Card updateCard(Integer cardId, Card card);
	void deleteCard(Integer cardId);
}
