package br.com.vr.miniautorizador.domain.repository;

import br.com.vr.miniautorizador.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
	// you can add custom query methods here if needed
}
