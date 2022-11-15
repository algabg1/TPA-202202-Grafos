/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import static java.lang.Float.parseFloat;
import java.nio.charset.Charset;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * @author Matheus Henrique Gonçalves Costa
 *         Matrícula: 20191bsi0069
 *
 * @author Ana Gabriella Gomes
 *         Matrícula: 20192BSI0290
 */
public class Principal {

    private Grafo<String> grafo;

    public List<String> LeLinhas(int Tam) {
        try {
            List<String> linhas;
            linhas = readAllLines(Paths.get("entrada.txt"), Charset.defaultCharset());
            return linhas;
        } catch (IOException ex) {
            return null;
        }
    };

    public List<String> LeArquivo(int Tam) {
        try {
            GeradorArquivosGrafo g = new GeradorArquivosGrafo();
            List<String> linhas;
            g.geraArquivo(10);
            linhas = readAllLines(Paths.get("entrada.txt"), Charset.defaultCharset());
            return linhas;
        } catch (IOException ex) {
            return null;
        }
    };

    /*
     * Monta Grafo - Recebe uma lista de String
     * Deve receber o resultado da leitura realizada ao abrir o programa
     * Ele limpa a árvore existente no programa (mera formalidade) e a preenche com
     * a informação recebida
     */
    private void montaGrafo(List<String> linhas) {
        // this.grafo.Limpa();

        int qtCidades = parseInt(linhas.get(0));
        this.grafo = new Grafo<String>(qtCidades);
        for (int i = 1; i <= qtCidades; i++) {
            /*
             * Primeiro insiro os vértices pegando a linha que está, sendo a primeira coluna
             * o index e a segunda seu "Nome"
             */
            this.grafo.adicionarVertice(linhas.get(i).split(";")[1]);
        }
        // Pego de onde parei, ou seja, tiro a linha 1 + quantidade de cidades que tem
        // no arquivo.
        for (int i = qtCidades + 1; i < linhas.size(); i++) {
            /*
             * Agora para pegar as arestas utilizo a seguinte lógica:
             * Linha = Origem
             * Coluna = Destino
             * Valor no String[i][j] = Peso referente à distancia de Origem para Destino
             * Quebrei a String "na mão" pro código ficar mais fácil de entender.
             */
            String[] colunas = linhas.get(i).split(";");
            // Fazendo variável para indicar a origem
            Integer origem = i - qtCidades - 1;
            for (int j = 0; j < colunas.length; j++) {
                /*
                 * Adicionando cada aresta conforme dito acima
                 * Valor no String[i][j] = Peso referente à distancia de Origem para Destino
                 */
                this.grafo.AdicionarAresta(origem, j, parseFloat(colunas[j].replace(",", ".")));
            }
        }
    }

    private void entradaUsuario(Grafo<String> grafo) {
        boolean para = false;
        clearConsole();

        Integer palavra = 0;
        while (para == false) {

            System.out.println("1)Obter cidades vizinhas.");
            System.out.println("2)Obter todos os caminhos a partir de uma cidade.");
            System.out.println("3)Calcular caminho mínimo");
            System.out.println("4)Calcular Árvore geradora mínima");
            System.out.println("5)Sair");
            Scanner ler = new Scanner(System.in);
            palavra = ler.nextInt();

            switch (palavra) {
                case 1:
                    CidadesVizinhas(grafo);
                    break;
                case 2:
                    ObterDestinos(grafo);
                    break;
                case 3:
                    CalcCaminhoMínimo(grafo);
                    break;
                case 4:
                    grafo = CalcAGM(grafo);
                    break;

                case 5:
                    para = true;
                    break;
            }
        }

    }

    private Grafo<String> CalcAGM(Grafo<String> grafo) {
        return grafo.ArvoreMinima();
    }

    private void CidadesVizinhas(Grafo<String> grafo) {
        System.out.println("Digite o código da cidade:");
        // try (Scanner ler = new Scanner(System.in)) {
        // String palavra = ler.next();
        // grafo.ObtemVizinhos(parseInt(palavra));
        // } catch (NumberFormatException e) {
        // e.printStackTrace();
        // }
        Scanner ler = new Scanner(System.in);
        String palavra = ler.next();
        grafo.ObtemVizinhos(parseInt(palavra));
    }

    private void ObterDestinos(Grafo<String> grafo) {
        System.out.println("Digite o código da cidade:");
        // try (Scanner ler = new Scanner(System.in)) {
        // String palavra = ler.next();
        // grafo.BuscaEmLargura(parseInt(palavra));
        // } catch (NumberFormatException e) {
        // e.printStackTrace();
        // }
        Scanner ler = new Scanner(System.in);
        String palavra = ler.next();
        grafo.BuscaEmLargura(parseInt(palavra));
    }

    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException ex) {
            // Handle any exceptions.
        }
    }

    public void CalcCaminhoMínimo(Grafo<String> grafo) {
        System.out.println("Digite o código da cidade de origem:");
        Scanner ler = new Scanner(System.in);
        String palavra1 = ler.next();
        System.out.println("Digite o código da cidade de destino:");
        String palavra2 = ler.next();

        grafo.CaminhoMinimo(parseInt(palavra1), parseInt(palavra2));

    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        List<String> arquivo = principal.LeArquivo(10);
        principal.montaGrafo(arquivo);
        principal.entradaUsuario(principal.grafo);
    }

}

/*
 * Arquivo de entrada
 * 
 * Seu programa deverá receber como entrada arquivos gerados pelo programa de
 * geração de arquivos disponibilizado como parte deste trabalho.
 * 
 * O programa gera um arquivo com uma lista de cidades e uma matriz indicando as
 * distâncias entre as cidades:
 * 
 * i. a primeira linha do arquivo tem apenas um número n inteiro indicando a
 * quantidade de cidades;
 * 
 * ii. Depois vem n linhas (onde n é exatamente o valor lido na primeira linha
 * do arquivo), cada uma trazendo o código(número inteiro) e o nome de uma
 * cidade;
 * 
 * iii. Depois, teremos outras n linhas, sendo que cada uma dessas linhas traz n
 * valores float, cada um indicando a distância entre duas cidades:
 * o primeiro valor da primeira linha traz o valor da distancia entre a primeira
 * cidade e ela mesma (ou seja, zero), o segundo valor da primeira linha traz a
 * distância da primeira cidade para a segunda,
 * o terceiro valor é a distância da primeira cidade para a terceira e assim
 * sucessivamente. Quando não há ligação direta entre duas cidades o valor da
 * distância será zero.
 */
