package br.com.vr.miniautorizador.domain.service.impl;

import br.com.vr.miniautorizador.domain.dto.CardDTO;
import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.exception.NotFoundException;
import br.com.vr.miniautorizador.domain.mapper.CardMapper;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

	@Autowired
	private final CardRepository cardRepository;

	public Card save(Card card){
		return cardRepository.save(card);
	}

	@Override
	public boolean validationCardAlreadyExists(String cardNumber) {
		Optional<Card> optionalCard = cardRepository.findByCardNumber(cardNumber);
		if (optionalCard.isPresent()) {
			throw new NotFoundException("Card found for card number: " + cardNumber);
		}
		return false;
	}

	public CardDTO getCard(Integer cardId) {
		Optional<Card> optionalCard = cardRepository.findById(cardId);

		if (optionalCard.isPresent()) {
			return CardMapper.toDTO(optionalCard.get());
		} else {
			throw new NotFoundException("Card not found with id " + cardId);
		}
	}

}
