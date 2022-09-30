package org.cursojava;

public class CalculadoraCustos {

	public static double custosTotais(PrecisaPagar[] itens) {
		
		double resultado = 0;
		
		for(PrecisaPagar item : itens) {
			resultado += item.custoPraEmpresa();
		}
		
		return resultado;
	}
}
