/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;

/**
 *
 * @author 20191bsi0069
 */

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private float arestas[][];
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

    public Grafo<T> MontaArvore() {
        return null;
    }

    private int obterIndiceVertice(T valor) {
        Vertice<T> v;
        for (var i = 0; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            return i;
        }

        // Se chegou aqui é porque não existe um vértice com esse valor
        return -1;
    }

    public boolean AdicionarAresta(String[] lista) {
        // pegar as vértices da lista principal para referenciar e criar a aresa aqui

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

    public void buscaEmLargura() {
        boolean marcados[] = new boolean[this.quantVertices];
        int atual = 0;
        ArrayList<Integer> fila = new ArrayList<Integer>();
        fila.add(atual);
        while (fila.size() > 0) {
            atual = fila.get(0);
            fila.remove(0);
            marcados[atual] = true;
            System.out.println(this.vertices.get(atual).getValor());
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
