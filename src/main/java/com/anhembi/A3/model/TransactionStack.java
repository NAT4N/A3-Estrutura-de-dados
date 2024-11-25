package com.anhembi.A3.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStack {
    private Node<Transaction> top;

    public void push(Transaction data) {
        Node<Transaction> newNode = new Node<>(data);
        if (top != null) {
            newNode.setNext(top);
            top.setPrev(newNode);
        }
        top = newNode;
    }

    public Transaction pop() {
        if (top == null) {
            return null;
        }
        Transaction data = top.getData();
        top = top.getNext();
        if (top != null) {
            top.setPrev(null);
        }
        return data;
    }

    public Transaction top() {
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
        Node<Transaction> current = top;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Transaction> current = top;
        sb.append("[\n");

        while (current != null) {
            sb.append(current);
            if (current.getNext() != null)
                sb.append(",\n");
            current = current.getNext();
        }

        sb.append("]\n");
        return sb.toString();
    }

}