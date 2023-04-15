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
	public boolean cardExists(String cardNumber) {
		// implementation depends on your database or data access layer
		// assuming that you have a CardRepository with a findByCardNumber method
		if (findByCardNumber(cardNumber) != null){
			return true;
		}
		return false;
	}

	public CardDTO findByCardNumber(String cardNumber) {
		Optional<Card> optionalCard = cardRepository.findByCardNumber(cardNumber);

		if (optionalCard.isPresent()) {
			return CardMapper.toDTO(optionalCard.get());
		} else {
			// Handle card not found error
			throw new NotFoundException("Card not found for card number: " + cardNumber);
		}
	}

}
