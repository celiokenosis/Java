package org.cursojava;

public class MaterialPermanente implements PrecisaPagar {

	public double custoUnitario;
	public int quantidade;
	
	
	public MaterialPermanente(double custoUnitario, int quantidade) {
		super();
		this.custoUnitario = custoUnitario;
		this.quantidade = quantidade;
	}


	public double custoPraEmpresa() {
		return custoUnitario * quantidade;
	}

}
