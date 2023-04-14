package br.com.vr.miniautorizador.domain.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {

	private Integer id;

	@NotNull
	private String cardNumber;

	@NotNull
	private String passwordCard;

	@NotNull
	private BigDecimal balance;

}
