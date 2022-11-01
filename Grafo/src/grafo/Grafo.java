/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;

/**
 * @author Matheus Henrique Gonçalves Costa
 *         Matrícula: 20191bsi0069
 * @author Ana Gabriella Gomes
 *         Matrícula: 20192BSI0290
 */

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private float arestas[][]; //utilizando matriz de adjacencias
    int quantVertices;

    public Grafo(int quantVertices) {
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new float[quantVertices][quantVertices];
        this.quantVertices = quantVertices;
    }

    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.add(novo);
        return novo;
    }

    private int obterIndiceVertice(T valor) {
        Vertice<T> v;
        for (var i = 0; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            if (v.getValor().equals(valor)) {
                return i;
            }
        }
        // Se chegou aqui é porque não existe um vértice com esse valor
        return -1;
    }

    public boolean AdicionarAresta(int Origem, int Destino, float Peso) {
        // pegar as vértices da lista principal para referenciar e criar a aresta aqui
        // As linhas significam a origem, ou seja, eu sei qual a linha quando vem de
        // fora
        if (Peso == 0.0)
            return false;
        T origem = this.vertices.get(Origem).getValor();
        T destino = this.vertices.get(Destino).getValor();
        this.adicionarAresta(origem, destino, Peso);
        return true;
    }

    private void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> verticeOrigem, verticeDestino;
        // Busco o indice do vértice com 0 valor de origem
        int indiceOrigem = obterIndiceVertice(origem);
        // Se ainda não existe vertice com o valor da origem, vou criar 0 vértice
        if (indiceOrigem == -1) {
            verticeOrigem = adicionarVertice(origem);
            indiceOrigem = this.vertices.indexOf(verticeOrigem);
        }
        // Busco o indice do vértice com 0 valor de origem
        int indiceDestino = obterIndiceVertice(destino);

        // Se ainda não existe vertice com o valor da origem, vou criar o vértice
        if (indiceDestino == -1) {
            verticeDestino = adicionarVertice(destino);
            indiceDestino = this.vertices.indexOf(verticeDestino);
        }
        this.arestas[indiceOrigem][indiceDestino] = peso;
    }

    public void BuscaEmLargura(int codCidade) {
        // Verifico se a cidade passada pelo usuário existe

        if (this.quantVertices <= codCidade) {
            System.out.println("Código inválido");
            return;
        }
        buscaEmLargura(codCidade-1);
    }

    private void buscaEmLargura(int origem) {
        boolean marcados[] = new boolean[this.quantVertices];
        int atual = 0;
        ArrayList<Integer> fila = new ArrayList<Integer>();
        ArrayList<T> visitados = new ArrayList<T>();
        T item;
        // Adicionando a cidade passada pelo usuário
        fila.add(origem);
        while (fila.size() > 0) {
            atual = fila.get(0);
            fila.remove(0);
            marcados[atual] = true;
            item = this.vertices.get(atual).getValor();
            // Para não repetir as cidades, coloquei-as em uma lista e verifico se a cidade
            // não está contida.
            if (visitados.indexOf(item) == -1) {
                System.out.println("Código:" + (atual+1) + " Cidade: " + item);
                visitados.add(item);
                for (int dest = 0; dest < this.quantVertices; dest++) {
                    // Se o nó é adjacente
                    if (arestas[atual][dest] > 0)
                        // Se ele ainda não foi visitado o coloco na fila
                        if (!marcados[dest])
                            fila.add(dest);
                }
            }
        }
    }

    public void Limpa() {
        this.vertices = new ArrayList<Vertice<T>>();
        this.quantVertices = 0;
        this.arestas = new float[quantVertices][quantVertices];
    }

    public void ObtemVizinhos(int codCidade) {
        if (this.quantVertices <= codCidade) {
            System.out.println("Código inválido");
            return;
        }
        obtemVizinhos(codCidade);
    }

    private void obtemVizinhos(int codCidade) {
        float distancia = 0;
        for (int dest = 0; dest < this.quantVertices; dest++) {
            // Se o nó é adjacente
            distancia = arestas[codCidade - 1][dest];
            if (distancia > 0) {
                System.out.println("Código: " + (dest + 1) + "; Cidade: " + this.vertices.get(dest).getValor()
                        + "; Distância: " + distancia);
            }

        }
    }

    //método etapa 2
    public void caminhoMinimo (T Origem, T Destino){
        //imprimir caminho mínimo

        //imprimir distância total entre os dois vertices
    }

}
