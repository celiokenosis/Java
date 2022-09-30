import java.math.BigDecimal;

public class ExecutarTeste {

	public static void main(String[] args) {
		
		Produto produto1 = new Produto();
		produto1.setId(1L);
		produto1.setNome("Módulo Orientação a Objeto");
		produto1.setValor(BigDecimal.valueOf(100));
		
		Produto produto2 = new Produto();
		produto2.setId(2L);
		produto2.setNome("Módulo Spring Boot API REST");
		produto2.setValor(BigDecimal.valueOf(300));
		
		Produto produto3 = new Produto();
		produto3.setId(3L);
		produto3.setNome("Módulo Angular 8");
		produto3.setValor(BigDecimal.valueOf(100));
		
		Produto produto4 = new Produto();
		produto4.setId(4L);
		produto4.setNome("Módulo Hibernate");
		produto4.setValor(BigDecimal.valueOf(300));
		
		/*Instancia uma nova venda*/
		Venda venda = new Venda();
		venda.setDescricaoVenda("Venda Curso Formação Java");
		venda.setEnderecoEntrega("Entrega pelo email");
		venda.setId(10L);
		venda.setNomeCliente("Alex Fernando");
		
		/*Adiciona os itens da venda na lista*/
		venda.getListaProdutos().add(produto1);
		venda.getListaProdutos().add(produto2);
		venda.getListaProdutos().add(produto3);
		venda.getListaProdutos().add(produto4);
		
		System.out.println("Valor da venda total: " + venda.getValorTotal());
		
	}
}
