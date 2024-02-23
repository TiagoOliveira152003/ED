/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

/**
 *
 * @author tiago
 */
public class Node<T> {

    private T data;
    private Node next;

    public T getData() {
        return data;
    }

    public void setData(T num) {
        this.data = num;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public Node(T num) {
        this.data = num;
    }

}
