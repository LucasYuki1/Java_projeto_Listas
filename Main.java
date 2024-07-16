import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean continuar = true;
        ArrayList<Produtos> compras = new ArrayList<>();

        try (Scanner leitura = new Scanner(System.in)) {
            System.out.println("Digite o limite do cartão: ");
            double limiteDoCartao = leitura.nextDouble();
            leitura.nextLine(); // Limpa a nova linha deixada por nextDouble()

            do {
                System.out.println("""
                        Escolha uma opção:
                        1- Comprar algum item
                        2- Verificar Carrinho
                        3- Verificar saldo restante
                        4- Sair do sistema
                        """);
                try {
                    int opcoes = leitura.nextInt();
                    leitura.nextLine(); // Limpa a nova linha deixada por nextInt()
                    switch (opcoes) {
                        case 1 -> {
                            System.out.println("Digite o nome do produto:");
                            String nome = leitura.nextLine();

                            System.out.println("Digite o Preço do produto:");
                            double preco = leitura.nextDouble();
                            leitura.nextLine(); // Limpa a nova linha deixada por nextDouble()

                            if (limiteDoCartao - preco < 0) {
                                System.out.println("""
                                        Saldo insuficiente!
                                        ********************
                                        Compras realizadas:
                                        """);
                                compras.forEach(System.out::println);

                                String mensagem = String.format("""
                                        ********************
                                        Saldo do cartão: %.2f
                                        """, limiteDoCartao);
                                System.out.println(mensagem);
                            } else {
                                System.out.println("Compra realizada com sucesso!");
                                limiteDoCartao -= preco;
                                var produto = new Produtos(nome, preco);
                                compras.add(produto);
                            }
                        }
                        case 2 -> {
                            System.out.println("********************************");
                            System.out.println("Carrinho de compras:");
                            compras.sort(Comparator.comparing(Produtos::getPreco));
                            compras.forEach(System.out::println);
                            System.out.println("********************************");
                        }
                        case 3 -> {
                            System.out.printf("Saldo restante do cartão: %.2f%n", limiteDoCartao);
                        }
                        case 4 -> {
                            continuar = false;
                        }
                        default -> {
                            System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        }
                    }
                } catch (InputMismatchException e) {
                    // Tratamento de valores inválidos
                    System.out.println("Por favor insira um valor válido");
                    leitura.nextLine(); // Limpa o buffer do scanner
                }
            } while (continuar);
        }
    }
}