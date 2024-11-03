package com.anhembi.demo.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionList {
    Node<Transacao> first, last;
    private int size;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void insert(Transacao data) {
        Node<Transacao> newNode = new Node<>(data);
        size++;

        if (isEmpty()) {
            first = last = newNode;
            return;
        }

        last.setNext(newNode);
        newNode.setPrev(last);
        last = newNode;
    }

    public Transacao removeFirst() {

        if (isEmpty()) {
            return null;
        }

        Transacao data = first.getData();
        first = first.getNext();

        if (first == null) {
            last = null;
        } else {
            first.setPrev(null);
        }

        size--;
        return data;
    }

    public Transacao removeLast() {
        if (isEmpty()) {
            return null;
        }

        Transacao data = last.getData();

        last = last.getPrev();
        if (last == null) {
            first = null;
        } else {
            last.setNext(null);
        }

        size--;
        return data;
    }

    public List<Transacao> print() {
        if (isEmpty()) {
            return null;
        }

        Node<Transacao> aux = first.getNext();
        List<Transacao> lista = new ArrayList<>();

        while (aux != null) {
            lista.add(aux.getData());
            aux = aux.getNext();
        }
        return lista;
    }

    public Transacao get(int position) {
        if(position <= 0 || position > size){
            return null;
        }
        int count = 1;
        Node<Transacao> aux = first;
        while (count < position) {
            aux = aux.getNext();
            count++;
        }
        return aux.getData();
    }

}