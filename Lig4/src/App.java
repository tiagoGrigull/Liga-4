import java.util.Scanner;

public class App {

    Scanner sc = new Scanner(System.in);

    int linhas = 6;
    int colunas = 7;
    String table[][] = new String[linhas][colunas];
    String jogador;
    String computador;

    public void escolhaCor() {
        int cor;
        do {
            System.out.println("Escolha um acor para jogar:");
            System.out.println("1- Vermelho");
            System.out.println("2- Amarelo");
            cor = sc.nextInt();

            if (cor != 1 && cor != 2) {
                System.out.println("Opção Inválida, tente novamente!");
            }
        } while (cor != 1 && cor != 2);

        if (cor == 1) {
            jogador = "V";
            computador = "A";
        } else {
            jogador = "A";
            computador = "V";
        }
    }

    public void iniciarTabuleiro() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                table[i][j] = "B";
            }
        }
    }

    public void mostrarTabuleiro() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println(". . . . . . .");
        System.out.println("1 2 3 4 5 6 7");
    }

    public boolean verificarCasa(int coluna) {
        return table[0][coluna].equals("B");
    }

    public void jogada() {
        System.out.println("Escolha uma coluna:");
        int num = sc.nextInt() - 1;
        if (!verificarCasa(num)) {
            System.out.println("Coluna Cheia");
            return;
        }
        for (int i = linhas - 1; i >= 0; i--) {
            if (table[i][num].equals("B")) {
                table[i][num] = jogador;
                break;
            }
        }
    }

    public void computadorJogada() {
        int num;

        do {
            num = (int) (Math.random() * colunas);
        } while (!verificarCasa(num));

        for (int i = linhas - 1; i >= 0; i--) {
            if (table[i][num].equals("B")) {
                table[i][num] = computador;
                break;
            }
        }
    }

    public boolean verificarVitoria(String peca) {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas - 3; j++) {
                if (table[i][j].equals(peca) &&
                        table[i][j + 1].equals(peca) &&
                        table[i][j + 2].equals(peca) &&
                        table[i][j + 3].equals(peca)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < linhas - 3; i++) {
            for (int j = 0; j < colunas; j++) {
                if (table[i][j].equals(peca) &&
                        table[i + 1][j].equals(peca) &&
                        table[i + 2][j].equals(peca) &&
                        table[i + 3][j].equals(peca)) {
                    return true;
                }
            }
        }

        for (int i = 0; i < linhas - 3; i++) {
            for (int j = 0; j < colunas - 3; j++) {
                if (table[i][j].equals(peca) &&
                        table[i + 1][j + 1].equals(peca) &&
                        table[i + 2][j + 2].equals(peca) &&
                        table[i + 3][j + 3].equals(peca)) {
                    return true;
                }
            }
        }

        for (int i = 3; i < linhas; i++) {
            for (int j = 0; j < colunas - 3; j++) {
                if (table[i][j].equals(peca) &&
                        table[i - 1][j + 1].equals(peca) &&
                        table[i - 2][j + 2].equals(peca) &&
                        table[i - 3][j + 3].equals(peca)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        App app = new App();

        app.iniciarTabuleiro();
        app.escolhaCor();

        while (true) {
            app.mostrarTabuleiro();
            app.jogada();

            if (app.verificarVitoria(app.jogador)) {
                app.mostrarTabuleiro();
                System.out.println("Você Venceu");
                break;
            }

            app.computadorJogada();
            if (app.verificarVitoria(app.computador)) {
                app.mostrarTabuleiro();
                System.out.println("Computador Venceu");
                break;
            }
        }
    }
}
