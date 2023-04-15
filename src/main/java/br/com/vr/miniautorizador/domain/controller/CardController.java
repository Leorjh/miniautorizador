package br.com.vr.miniautorizador.domain.controller;

import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.exception.NotFoundException;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CardController {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CardServiceImpl cardService;

//	@PostMapping
//	public ResponseEntity<Object> createCard(@RequestBody Card card) {
//		try {
//			if (cardService.cardAlreadyExists(card.getCardNumber())) {
//				// return a 422 Unprocessable Entity response if the card already exists
//				return ResponseEntity.unprocessableEntity().build();
//			}
//
//			Card savedCard = cardService.save(card);
//
//			// return a 201 Created response with the created card in the body
//			URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(savedCard.getId()).toUri();
//			return ResponseEntity.created(location).body(savedCard);
//		} catch (NotFoundException e) {
//			// Handle card not found error
//			return ResponseEntity.unprocessableEntity().body(e.getMessage());
//		}
//	}

	@PostMapping
	public ResponseEntity<Object> createCard(@RequestBody Card card) {
		try {
			if (cardService.validationCardAlreadyExists(card.getCardNumber())) {
				// return a 422 Unprocessable Entity response if the card already exists
				return ResponseEntity.unprocessableEntity().build();
			}

			Card newCard = new Card(card.getCardNumber(), card.getPasswordCard());
			cardService.save(newCard);

			// return a 201 Created response with the created card in the body
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCard.getId()).toUri();
			return ResponseEntity.created(location).body(newCard);
		} catch (NotFoundException e) {
			// Handle card not found error
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}



}
