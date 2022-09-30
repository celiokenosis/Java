package org.cursojava;

import java.sql.Date;

public class Principal {

	public static final long HOJE = System.currentTimeMillis();
	public static final long DIA = 1000*60*60*24;
	
	
	public static void main(String[] args) {

		PrecisaPagar[] paraEsseMes = {
				
				new Empregado(1000),
				new Gerente(3000,6000),
				new Conta(100,new Date(HOJE - DIA)),
				new Conta(300,new Date(HOJE + DIA)),
				new Empregado(1500),
				new Empregado(4000),
				new Gerente(6000,18000),
				new Conta(950,new Date(HOJE - 10*DIA)),
				new MaterialPermanente(2500, 4)/*10 computador a 2.500,00 cada*/
		};

		double valor = CalculadoraCustos.custosTotais(paraEsseMes);
		
		System.out.println("O custo total do mês é: R$ " + valor);
	}

}
