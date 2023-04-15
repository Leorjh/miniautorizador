package br.com.vr.miniautorizador.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "card_number")
	private String cardNumber;

	@NotNull
	@Column(name = "password_card")
	private String passwordCard;

	@NotNull
	@Column(name = "balance")
	private BigDecimal balance;

	public Card(String cardNumber, String passwordCard) {
		this.cardNumber = cardNumber;
		this.passwordCard = passwordCard;
		this.balance = BigDecimal.valueOf(500);
	}

}
