package br.com.vr.miniautorizador.domain.service;

import br.com.vr.miniautorizador.domain.entity.Card;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

	Card save(Card card);
}
