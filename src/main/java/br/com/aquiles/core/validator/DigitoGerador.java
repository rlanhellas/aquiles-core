package br.com.aquiles.core.validator;

import java.util.Random;

/**
 * Classe responsavel pela geracao randomica de digitos
 */
public class DigitoGerador {
	private static final Random RANDOM = new Random();

	public String generate(int quantidade) {
		final StringBuilder digitos = new StringBuilder();
		for (int i = 0; i < quantidade; i++) {
			digitos.append(RANDOM.nextInt(10));
		}
		return digitos.toString();
	}
}
