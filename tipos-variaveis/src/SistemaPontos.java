import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RegistroPonto {
    private LocalTime chegada;
    private LocalTime saida;

    public RegistroPonto(LocalTime chegada, LocalTime saida) {
        this.chegada = chegada;
        this.saida = saida;
    }

    public LocalTime getChegada() {
        return chegada;
    }

    public LocalTime getSaida() {
        return saida;
    }
}

public class SistemaPontos {

    public static void limparTela() {
        // Comando para limpar a tela no Windows, Mac e Linux
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar a tela.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        List<RegistroPonto> registros = new ArrayList<>();

        boolean reiniciar = true;

        while (reiniciar) {
            boolean continuar = true;
            registros.clear(); // Limpa os registros no início de cada iteração de reinício

            while (continuar) {
                System.out.print("Digite o horário de chegada (HH:mm): ");
                String chegadaStr = scanner.next();
                LocalTime chegada = LocalTime.parse(chegadaStr, formatter);

                System.out.print("Digite o horário de saída (HH:mm): ");
                String saidaStr = scanner.next();
                LocalTime saida = LocalTime.parse(saidaStr, formatter);

                registros.add(new RegistroPonto(chegada, saida));

                System.out.print("Deseja registrar outro ponto? (s/n): ");
                String resposta = scanner.next();
                continuar = resposta.equalsIgnoreCase("s");
            }

            System.out.println("Tabela de Registros de Ponto:");
            System.out.printf("%-30s%-30s%n", "Chegada", "Saída");
            for (RegistroPonto registro : registros) {
                System.out.printf("%-30s%-30s%n", registro.getChegada().format(formatter), registro.getSaida().format(formatter));
            }

            System.out.print("Deseja reiniciar o registro? (s/n): ");
            String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("n")) {
                reiniciar = false;
                System.out.println("Programa finalizado!");
            } else {
                limparTela(); // Limpa a tela antes de reiniciar
            }
        }

        scanner.close();
    }
}
