
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projeto.de.ed.Interfaces;

import projeto.de.ed.exceptions.ElementNotFoundException;

/**
 *
 * @param <T> tipo genérico
 */
public interface NetworkADT<T extends Object> extends GraphADT<T> {

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @param weight the weight
     * @throws ElementNotFoundException lança exceção
     */
    public void addEdge(T vertex1, T vertex2, double weight) throws ElementNotFoundException;

    /**
     * Returns the weight of the shortest path in this network.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return the weight of the shortest path in this network
     * @throws ElementNotFoundException lança exceção
     */
    public double shortestPathWeight(T vertex1, T vertex2) throws ElementNotFoundException;
}

