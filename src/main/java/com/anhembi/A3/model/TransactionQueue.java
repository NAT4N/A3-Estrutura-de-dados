package com.anhembi.A3.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionQueue {
    Node<Transaction> first, last;
    private int size;

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void insert(Transaction data) {
        Node<Transaction> newNode = new Node<>(data);
        size++;

        if (isEmpty()) {
            first = last = newNode;
            return;
        }

        last.setNext(newNode);
        newNode.setPrev(last);
        last = newNode;
    }

    public Transaction removeFirst() {

        if (isEmpty()) {
            return null;
        }

        Transaction data = first.getData();
        first = first.getNext();

        if (first == null) {
            last = null;
        } else {
            first.setPrev(null);
        }

        size--;
        return data;
    }

    public Transaction removeLast() {
        if (isEmpty()) {
            return null;
        }

        Transaction data = last.getData();

        last = last.getPrev();
        if (last == null) {
            first = null;
        } else {
            last.setNext(null);
        }

        size--;
        return data;
    }

    public List<Transaction> print() {
        if (isEmpty()) {
            return null;
        }

        Node<Transaction> aux = first.getNext();
        List<Transaction> queue = new ArrayList<>();

        while (aux != null) {
            queue.add(aux.getData());
            aux = aux.getNext();
        }
        return queue;
    }

    public Transaction get(int position) {
        if(position <= 0 || position > size){
            return null;
        }
        int count = 1;
        Node<Transaction> aux = first;
        while (count < position) {
            aux = aux.getNext();
            count++;
        }
        return aux.getData();
    }

}