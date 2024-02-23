/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import projeto.de.ed.Interfaces.QueueADT;
import projeto.de.ed.exceptions.EmptyCollectionException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @param <T> tipo genérico
 */
public class LinkedQueue<T> implements QueueADT<T>, Iterable<T> {

    private LinearNode<T> first;
    private LinearNode<T> last;
    private int count;

    /**
     * Criar uma LinkedQueue vazia
     */
    public LinkedQueue() {
        this.first = null;
        this.last = null;
        this.count = 0;
    }

    /**
     * inicializa a LinkedQueue com um elemento
     *
     * @param element
     */
    private void initialize(T element) {
        LinearNode<T> aux = new LinearNode<>(element);
        this.last = aux;
        this.first = aux;
        this.count++;
    }

    /**
     * Metodo para adicionar elemento à queue
     *
     * @param element elemento a inserir na queue
     */
    @Override
    public void enqueue(T element) {
        if (this.count == 0) {
            initialize(element);
        } else {
            LinearNode<T> aux = new LinearNode<>(element);
            this.first.setNext(aux);
            this.first = aux;
            count++;
        }
    }

    /**
     * Metodo para remover o elemento da queue
     *
     * @return elemento removido
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (this.count == 0) {
            throw new EmptyCollectionException("Queue Vazia.");
        }
        if (this.count == 1) {
            T temp = this.last.getData();
            this.last = this.first = null;
            this.count--;
            return temp;
        } else {
            T temp = this.last.getData();
            this.last = this.last.getNext();
            this.count--;
            return temp;
        }
    }

    /**
     * Metodo para devolver primeiro elemento da queue
     *
     * @return primeiro elemento da queue
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Queue Vazia.");
        }

        return this.last.getData();

    }

    /**
     * Metodo para verificar se a queue esta vazia
     *
     * @return true se a queue estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Método responsável por devolver o tamanho da queue
     *
     * @return  tamanho da queue
     */
    @Override
    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String string = "";

        if (this.last != null) {
            boolean value = true;
            LinearNode aux = this.last;

            while (value) {
                string += aux.getData().toString() + "\n";
                if (aux.getNext() != null) {
                    aux = aux.getNext();
                } else {
                    value = false;
                }
            }
        } else {
            return "Lista Vazia";
        }

        return string;
    }

    /**
     *
     * @return um iterator para os elementos da LinkedQueue
     */
    @Override
    public Iterator<T> iterator() {
        QueueIterador it = new QueueIterador();
        return it;
    }

    /**
     * Classe auxiliar para o iterator (inner class)
     */
    public class QueueIterador implements Iterator<T> {

        private LinearNode<T> current = last;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T item = this.current.getData();
            this.current = current.getNext();
            return item;
        }
    }
}

