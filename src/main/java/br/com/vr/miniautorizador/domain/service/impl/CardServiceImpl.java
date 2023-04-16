package br.com.vr.miniautorizador.domain.service.impl;

import br.com.vr.miniautorizador.domain.dto.CardDTO;
import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.exception.CardException;
import br.com.vr.miniautorizador.domain.mapper.CardMapper;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private final CardRepository cardRepository;

	public CardServiceImpl(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public Card save(Card card){
		return cardRepository.save(card);
	}

	@Override
	public boolean validationCardAlreadyExists(String cardNumber) {
		Optional<Card> optionalCard = cardRepository.findByCardNumber(cardNumber);
		if (optionalCard.isPresent()) {
			throw new CardException("Card found for card number: " + cardNumber);
		}
		return false;
	}

	public CardDTO getCard(Integer cardId) {
		Optional<Card> optionalCard = cardRepository.findById(cardId);

		if (optionalCard.isPresent()) {
			return CardMapper.toDTO(optionalCard.get());
		} else {
			throw new CardException("Card not found with id " + cardId);
		}
	}

	@Override
	public List<CardDTO> getAllCards() {
		List<Card> cardList = cardRepository.findAll();
		if (cardList.isEmpty()) {
			throw new CardException("No cards found");
		}
		return cardList.stream().map(CardMapper::toDTO).collect(Collectors.toList());
	}

	public BigDecimal getCardBalance(String cardNumber) {
		Card card = cardRepository.findByCardNumber(cardNumber)
			.orElseThrow(() -> new CardException("Card not found"));
		return card.getBalance();
	}

	public void deleteCard(Integer cardId) throws CardException {
		Optional<Card> optionalCard = cardRepository.findById(cardId);

		if (optionalCard.isPresent()) {
			Card card = optionalCard.get();
			cardRepository.delete(card);
		} else {
			throw new CardException("Card not found with id " + cardId);
		}
	}

	public Card updateCard(Integer cardId, Card card) throws CardException {
		Optional<Card> optionalCard = cardRepository.findById(cardId);

		if (optionalCard.isPresent()) {
			Card existingCard = optionalCard.get();
			card.setCardNumber(existingCard.getCardNumber());
			if (card.getPasswordCard().equals(existingCard.getPasswordCard())) {
				existingCard.setPasswordCard(card.getPasswordCard());
			}else{
				throw new CardException("The password provided for card " + existingCard.getCardNumber() + " is incorrect. Please try again with the correct password.");
			}
			existingCard.setBalance(card.getBalance() != null ? card.getBalance() : existingCard.getBalance());
			return cardRepository.save(existingCard);
		} else {
			throw new CardException("Card not found with id " + cardId);
		}
	}

}
