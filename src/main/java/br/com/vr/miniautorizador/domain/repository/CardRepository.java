package br.com.vr.miniautorizador.domain.repository;

import br.com.vr.miniautorizador.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

	Optional<Card> findByCardNumber(String cardNumber);

}
