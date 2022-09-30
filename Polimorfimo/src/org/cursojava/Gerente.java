package org.cursojava;

public class Gerente extends Empregado {

	private double bonusAnual;
	
	public Gerente(double salario, double bonusAnual) {
		super(salario);
		this.bonusAnual = bonusAnual;
	}

	@Override
	public double custoPraEmpresa() {
		return super.custoPraEmpresa() + bonusAnual/12;
	}
	
}
