package com.anhembi.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pilha<Transacao> {
    private Node<Transacao> top;

    public void push(Transacao data) {
        Node<Transacao> newNode = new Node<>(data);
        if (top != null) {
            newNode.setNext(top);
            top.setPrev(newNode);
        }
        top = newNode;
    }

    public Transacao pop() {
        if (top == null) {
            return null;
        }
        Transacao data = top.getData();
        top = top.getNext();
        if (top != null) {
            top.setPrev(null);
        }
        return data;
    }

    public Transacao top() {
        if (top == null) {
            return null;
        }
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        int count = 0;
        Node<Transacao> current = top;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Transacao> current = top;
        while (current != null) {
            sb.append(current.toString());
            sb.append("\n");
            current = current.getNext();
        }
        return sb.toString();
    }

}