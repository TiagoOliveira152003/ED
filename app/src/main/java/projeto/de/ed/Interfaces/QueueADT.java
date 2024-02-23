/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projeto.de.ed.Interfaces;

import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * @param <T> tipo genérico
 */
public interface QueueADT<T extends Object> {

    /**
     * Adds one element to the rear of this queue.
     *
     * @param element the element to be added to the rear of this queue
     */
    public void enqueue(T element);

    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws EmptyCollectionException lança exceção
     */
    public T dequeue() throws EmptyCollectionException;

    /**
     * Returns without removing the element at the front of this queue.
     *
     * @return the first element in this queue
     * @throws EmptyCollectionException lança exceção
     */
    public T first() throws EmptyCollectionException;

    /**
     * Returns true if this queue contains no elements.
     *
     * @return true if this queue is empty
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in this queue.
     *
     * @return the integer representation of the size of this queue
     */
    public int size();

    /**
     * Returns a string representation of this queue.
     *
     * @return the string representation of this queue
     */
    @Override
    public String toString();
}

