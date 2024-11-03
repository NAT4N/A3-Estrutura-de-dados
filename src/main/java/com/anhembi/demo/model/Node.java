package com.anhembi.demo.model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Node<T> {
    private Transacao data;
    private Node<Transacao> next, prev;

    public Node(Transacao data) {
        this.data = data;
    }

    public Node(Transacao data, Node<Transacao> next, Node<Transacao> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}