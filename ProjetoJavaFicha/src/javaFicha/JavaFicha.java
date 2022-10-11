package javaFicha;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaFicha {
	
	private static String nome;
	private static int idade;
	private static float altura;
	private static float peso;
	private static char sexo;
	
	public static void main(String[] args) {
		
		Scanner leitura = new Scanner(System.in);
		
		/*nome*/
		do{
			System.out.println("Digite seu nome: ");
			nome = leitura.nextLine();
		}while(nome.length() < 6 || nome.length() > 30);/*Executa até informar corretamente nr. caracteres*/
		
		/*idade*/
		do {
			System.out.println("Digite sua idade: ");/*Idade ente 0 e 100 anos*/
			idade = leitura.nextInt();
		}while(idade <=0 || idade >=100	);
		
		/*altura*/
		do {
			System.out.println("Digite sua altura: ");
			altura = leitura.nextFloat();
		}while (altura <=0);
		
		/*peso*/
		do {
			System.out.println("Digite o seu peso: ");
			peso = leitura.nextFloat();
		}while (peso <=0);
		
		/*sexo*/
		do {
			try {
				System.out.println("Digite seu sexo: (F)eminino ou (M)asculino");
				sexo = (char) System.in.read();
			} catch (IOException ex) {
				Logger.getLogger(JavaFicha.class.getName()).log(Level.SEVERE,null,ex);
			}
		}while(sexo != 'F' && sexo != 'M');
		
		System.out.println("Nome: " + nome);
		System.out.println("Idade: " + idade);
		System.out.println("Altura: " + altura);
		System.out.println("Peso: " + peso);
		System.out.println("Sexo: " + sexo);
	}
}
