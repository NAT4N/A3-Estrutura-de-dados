package com.anhembi.demo.model;

public class TransactionList<T> {
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
        // 1. lista vazia
        if (isEmpty()) {
            return null;
        }

        Transacao data = first.getData();
        first = first.getNext();

        // 2. só tem um nó
        if (first == null) {
            last = null;
        } else {
            // 3. caso geral
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
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Lista Vazia";
        }

        String dataString = first.getData().toString();
        Node<Transacao> aux = first.getNext();

        while (aux != null) {
            dataString += "-" + aux.getData();
            aux = aux.getNext();
        }
        return dataString;
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