import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomeProjeto = scanner.nextLine();

        String nomeEmMaiusculas = nomeProjeto.toUpperCase();
        int comprimento = nomeProjeto.length();

        System.out.println(nomeEmMaiusculas + " " + comprimento);

        scanner.close();
    }
}