package br.com.vr.miniautorizador.domain.controller;

import br.com.vr.miniautorizador.domain.dto.CardDTO;
import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.exception.NotFoundException;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cartoes")
public class CardController {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private CardServiceImpl cardService;

	@GetMapping
	public ResponseEntity<Object> getAllCards() {
		try {
			List<CardDTO> cards = cardService.getAllCards();
			return ResponseEntity.ok().body(cards);
		} catch (NotFoundException e) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Object> getCard(@PathVariable Integer id) {
		try {
			CardDTO card = cardService.getCard(id);
			return ResponseEntity.ok().body(card);
		} catch (NotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Object> createCard(@RequestBody Card card) {
		try {
			if (cardService.validationCardAlreadyExists(card.getCardNumber())) {
				return ResponseEntity.unprocessableEntity().build();
			}

			Card newCard = new Card(card.getCardNumber(), card.getPasswordCard());
			cardService.save(newCard);

			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCard.getId()).toUri();
			return ResponseEntity.created(location).body(newCard);
		} catch (NotFoundException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@GetMapping("/{numeroCartao}")
	public ResponseEntity<BigDecimal> getCardBalance(@PathVariable String numeroCartao) {
		try {
			BigDecimal balance = cardService.getCardBalance(numeroCartao);
			return ResponseEntity.ok(balance);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}



}
