/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

/**
 *
 * @author 20191bsi0069
 */
public class Vertice<T> {
    final T valor;

    public Vertice(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return this.valor;
    }

    public Boolean CompareTo(T Comparado) {
        if (this.valor == Comparado) {
            return true;
        }
        return false;
    }

}
