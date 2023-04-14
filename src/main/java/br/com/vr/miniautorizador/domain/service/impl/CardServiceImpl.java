package br.com.vr.miniautorizador.domain.service.impl;

import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

	private final CardRepository cardRepository;

	public Card save(Card card){
		return cardRepository.save(card);
	}
}
