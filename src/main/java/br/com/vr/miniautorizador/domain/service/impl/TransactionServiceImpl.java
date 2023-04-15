package br.com.vr.miniautorizador.domain.service.impl;

import br.com.vr.miniautorizador.domain.dto.CardDTO;
import br.com.vr.miniautorizador.domain.dto.TransactionDTO;
import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.exception.NotFoundException;
import br.com.vr.miniautorizador.domain.mapper.CardMapper;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl {

	@Autowired
	private final CardRepository cardRepository;

}
