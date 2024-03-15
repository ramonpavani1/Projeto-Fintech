package fintech;

import java.util.Scanner;

public class fintechAPI extends Conta{

	public fintechAPI() {
	}
	public static void main(String[] args) {
		try (Scanner tech = new Scanner(System.in)) {
			System.out.println("Qual a sua Meta: ");
			setMeta(tech.nextInt());
			System.out.println("Qual o valor inicial: ");
			setValorInicial(tech.nextInt());
			System.out.println("Adicione seus gastos: ");
			setCPF(tech.nextInt());
			System.out.println("Digite seu Telefone: ");
			setTelefone(tech.next());
			System.out.println("Digite o seu E-mail: ");
			setE_mail(tech.next());
			System.out.println("Digite a sua Data de Nascimento: ");
			setDateNasc(tech.next());
			System.out.println("Digite a sua Senha: ");
			setSenha(tech.nextInt());
	}
	}
}
