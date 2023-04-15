package br.com.vr.miniautorizador.domain.service;

import br.com.vr.miniautorizador.domain.dto.CardDTO;
import br.com.vr.miniautorizador.domain.entity.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

	Card save(Card card);

	boolean validationCardAlreadyExists(String cardNumber);

	CardDTO getCard(Integer cardId);

}
