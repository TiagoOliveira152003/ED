/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import projeto.de.ed.Interfaces.StackADT;
import projeto.de.ed.exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <T> tipo genérico
 */
public class LinkedStack<T> implements StackADT<T>, Iterable<T> {

    private LinearNode<T> top;
    private int size;

    /**
     * Cria uma LinkedStack vazia
     */
    public LinkedStack() {
        this.size = 0;
        this.top = null;
    }

    /**
     * Adiciona um elemento à LinkedStack
     *
     * @param t elemento a ser adicionado a LinkedStack
     */
    @Override
    public void push(T t) {
        LinearNode<T> tempNode = new LinearNode<>(t);

        tempNode.setNext(this.top);
        this.top = tempNode;
        this.size++;
    }

    /**
     * Remove e retorna um elemento da LinkedStack
     *
     * @return elemento removido da Stack
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia.");
        }
        T tempNode = this.top.getData();
        this.top = this.top.getNext();
        this.size--;
        return tempNode;
    }

    /**
     * Retorna o elemento que esta no topo da LinkedStack
     *
     * @return elemento que esta no topo da LinkedStack
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Lista Vazia");
        }
        return this.top.getData();
    }

    /**
     * Verifica se a Stack está vazia
     *
     * @return true se a LinkedStack estiver vazia, false caso contrario
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Permite descobrir o numero de elementos presentes na LinkedStack
     *
     * @return o numero de elementos presentes na LinkedStack
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Retorna uma representação em string da LinkedStack
     *
     * @return uma representação em string da LinkedStack
     */
    @Override
    public String toString() {
        String result = "";
        LinearNode current = this.top;

        while (current != null) {
            result = result + (current.getData()).toString() + "\n";
            current = current.getNext();
        }

        return result;
    }

    /**
     *
     * @return um iterator para os elementos da LinkedStack
     */
    @Override
    public Iterator<T> iterator() {
        StackIterador it = new StackIterador();
        return it;
    }

    /**
     * Classe auxiliar para o metodo iterator (inner class)
     */
    public class StackIterador implements Iterator<T> {

        private LinearNode<T> current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T item = current.getData();
            current = current.getNext();
            return item;
        }
    }
}

