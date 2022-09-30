package org.cursojava;
import java.util.Date;

public class Conta implements PrecisaPagar{

	private double valor;
	private Date vencimento;

	
	public Conta(double valor, Date vencimento) {
		this.valor = valor;
		this.vencimento = vencimento;
	}


	public double custoPraEmpresa() {
		
		double total = valor;
		
		if(vencimento.getTime() < System.currentTimeMillis()) {
			/*Conta atrasada*/
			total *= 1.1; /*10%*/
		}
		
		return total;
		
	}
}
