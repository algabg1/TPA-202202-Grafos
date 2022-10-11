/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.nio.charset.Charset;
import static java.nio.file.Files.readAllLines;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author 20191bsi0069
 */
public class Principal {

    private Grafo<String> grafo;

    public List<String> LeLinhas(int Tam) {
        try {
            GeradorArquivosGrafo g = new GeradorArquivosGrafo();
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
            g.geraArquivo(Tam);
            linhas = readAllLines(Paths.get("entrada.txt"), Charset.defaultCharset());
            return linhas;
        } catch (IOException ex) {
            return null;
        }
    };

    private void montaGrafo(List<String> linhas) {
        int qtCidades = parseInt(linhas.get(0));
        for (int i = 1; i < qtCidades; i++) {

            this.grafo.adicionarVertice(linhas.get(i).split(";")[1]);
        }

        for (int i = qtCidades; i < linhas.size(); i++) {
            this.grafo.AdicionarAresta(linhas.get(i).split(";"));
        }
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        List<String> arquivo = principal.LeArquivo(10);
        principal.montaGrafo(arquivo);
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
