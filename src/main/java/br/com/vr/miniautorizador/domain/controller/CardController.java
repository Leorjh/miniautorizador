package br.com.vr.miniautorizador.domain.controller;

import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartoes")
public class CardController {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	CardService cardService;
}
