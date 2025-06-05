package it.nextre.nextcart.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidatorCampi {
	
	public static void validaCampoObbligatorio(String valore, String nomeCampo) {
		if (valore == null || valore.trim().isEmpty()) {
			throw new IllegalArgumentException("Il campo: " + nomeCampo + " è obbligatorio");
			}
		
	}
	
	public static LocalDate validaData(String valore, String nomeCampo) {
		
		try {
			return LocalDate.parse(valore);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Il campo: " + nomeCampo + " deve essere una data valida");

		}

		
	}

}
