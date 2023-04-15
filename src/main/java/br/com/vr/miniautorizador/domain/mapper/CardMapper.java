package br.com.vr.miniautorizador.domain.mapper;

import br.com.vr.miniautorizador.domain.dto.CardDTO;
import br.com.vr.miniautorizador.domain.entity.Card;

public class CardMapper {

	private CardMapper() {}

	public static CardDTO toDTO(Card card) {
		return CardDTO.builder()
			.id(card.getId())
			.cardNumber(card.getCardNumber())
			.passwordCard(card.getPasswordCard())
			.balance(card.getBalance())
			.build();
	}
}

