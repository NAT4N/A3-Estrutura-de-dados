package com.anhembi.A3.model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Node<T> {
    private Transaction data;
    private Node<Transaction> next, prev;

    public Node(Transaction data) {
        this.data = data;
    }

    public Node(Transaction data, Node<Transaction> next, Node<Transaction> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}