/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

/**
 *
 * @author User
 */
public class LinearNode <T> {
    private T data;
    private LinearNode<T> next, previous;

    public LinearNode() {
        this.data = null;
        this.next = null;
        this.previous = null;
    }
    
    public LinearNode(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public LinearNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(LinearNode<T> previous) {
        this.previous = previous;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(LinearNode next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public LinearNode getNext() {
        return next;
    }
}
