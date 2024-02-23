/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projeto.de.ed.Interfaces;

import projeto.de.ed.exceptions.ElementNotFoundException;

import java.util.Iterator;

/**
 * @param <T> tipo genérico
 */
public interface GraphADT<T extends Object> {

    /**
     * Adds a vertex to this graph, associating object with vertex.
     *
     * @param vertex the vertex to be added to this graph
     */
    void addVertex(T vertex);

    /**
     * Removes a single vertex with the given value from this graph.
     *
     * @param vertex the vertex to be removed from this graph
     * @throws ElementNotFoundException lança exceção
     */
    public void removeVertex(T vertex) throws ElementNotFoundException;

    /**
     * Inserts an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws ElementNotFoundException lança exceção
     */
    public void addEdge(T vertex1, T vertex2) throws ElementNotFoundException;

    /**
     * Removes an edge between two vertices of this graph.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws ElementNotFoundException lança exceção
     */
    public void removeEdge(T vertex1, T vertex2) throws ElementNotFoundException;

    /**
     * Returns a breadth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a breadth first iterator beginning at * the given vertex
     */
    Iterator iteratorBFS(T startVertex);

    /**
     * Returns a depth first iterator starting with the given vertex.
     *
     * @param startVertex the starting vertex
     * @return a depth first iterator starting at the * given vertex
     */
    Iterator iteratorDFS(T startVertex);

    /**
     * Returns an iterator that contains the shortest path between the two
     * vertices.
     *
     * @param startVertex  the starting vertex
     * @param targetVertex the ending vertex
     * @return an iterator that contains the shortest * path between the two
     * vertices
     */
    Iterator iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Returns true if this graph is empty, false otherwise.
     *
     * @return true if this graph is empty
     */
    public boolean isEmpty();

    /**
     * Returns true if this graph is connected, false otherwise.
     *
     * @return true if this graph is connected
     */
    public boolean isConnected();

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the integer number of vertices in this graph
     */
    public int size();

    public T[] getVertices();

    /**
     * Returns a string representation of the adjacency matrix.
     *
     * @return a string representation of the adjacency matrix
     */
    @Override
    public String toString();
}

