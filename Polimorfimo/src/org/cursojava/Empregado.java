package org.cursojava;

public class Empregado implements PrecisaPagar {

	private double salario;
	
	
	
	public Empregado(double salario) {
		this.salario = salario;
	}



	public double custoPraEmpresa() {
		// TODO Auto-generated method stub
		return salario * 2;
	}

}
