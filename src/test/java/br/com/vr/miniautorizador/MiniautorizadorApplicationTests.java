package br.com.vr.miniautorizador;

import br.com.vr.miniautorizador.domain.entity.Card;
import br.com.vr.miniautorizador.domain.exception.CardException;
import br.com.vr.miniautorizador.domain.repository.CardRepository;
import br.com.vr.miniautorizador.domain.service.impl.CardServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MiniautorizadorApplicationTests {

	@Mock
	private CardRepository cardRepository;

	@InjectMocks
	private CardServiceImpl cardService;

	@BeforeEach
	public void setup() {
		cardService = new CardServiceImpl(cardRepository);
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testValidationCardAlreadyExistsWhenCardDoesNotExist() {
		String cardNumber = "123";
		when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.empty());

		boolean result = cardService.validationCardAlreadyExists(cardNumber);

		assertFalse(result);
		verify(cardRepository, times(1)).findByCardNumber(cardNumber);
	}

	@Test
	public void testValidationCardAlreadyExistsWhenCardExists() {
		String cardNumber = "123";
		Card card = new Card(cardNumber, "pass123");
		when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(card));

		assertThrows(CardException.class, () -> cardService.validationCardAlreadyExists(cardNumber));
		verify(cardRepository, times(1)).findByCardNumber(cardNumber);
	}

	@Test
	public void testGetCardBalanceWhenCardExists() {
		String cardNumber = "123";
		Card card = new Card(cardNumber, "pass123");
		BigDecimal balance = card.getBalance();
		when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.of(card));

		BigDecimal result = cardService.getCardBalance(cardNumber);

		assertEquals(balance, result);
		verify(cardRepository, times(1)).findByCardNumber(cardNumber);
	}

	@Test
	public void testGetCardBalanceWhenCardDoesNotExist() {
		String cardNumber = "123";
		when(cardRepository.findByCardNumber(cardNumber)).thenReturn(Optional.empty());

		assertThrows(CardException.class, () -> cardService.getCardBalance(cardNumber));
		verify(cardRepository, times(1)).findByCardNumber(cardNumber);
	}

	@Test
	public void testInsertCardOk() {
		Card card = new Card("123", "pass123");

		when(cardRepository.save(any(Card.class))).thenReturn(card);

		Card result = cardService.save(card);

		assertNotNull(result);
		assertEquals("123", result.getCardNumber());
		assertEquals("pass123", result.getPasswordCard());
		assertEquals(BigDecimal.valueOf(500), result.getBalance());
		verify(cardRepository, times(1)).save(any(Card.class));
	}

}
