package com.rlsp.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor // Gera um constuctor com TODOS ATRIBUTOS da classe
@NoArgsConstructor // Construtor Padrao [SEM ATRIBUTOS]
public class Especificacao {

	@NotBlank
	@Column(name="especificacao_nome", length = 100, nullable = false)
	private String nome;
	@NotBlank
	@Column(name="especificacao_valor")
	private String valor;

}
