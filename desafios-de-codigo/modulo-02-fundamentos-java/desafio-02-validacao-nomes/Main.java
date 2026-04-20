import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomeProjeto = scanner.nextLine();

        String nomeTratado = nomeProjeto.trim();

        if (nomeTratado.isEmpty()) {
            System.out.println("INVALIDO");
        } else {
            System.out.println(nomeTratado.toUpperCase());
        }

        scanner.close();
    }
}