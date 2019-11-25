package ua.edu.ucu.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue implements Iterable<String> {
    ImmutableLinkedList list;

    public Queue() {
        this.list = new ImmutableLinkedList();
    }

    public Object peek() {
        return this.list.getFirst();
    }

    public void enqueue(Object e) {
        this.list = this.list.addLast(e);
    }

    public Object dequue() {
        Object res = this.peek();
        this.list = this.list.removeFirst();
        return res;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<String> iterator() {
        return new QueueIterator(this);
    }
}

class QueueIterator implements Iterator<String> {
    private ImmutableLinkedList list;

    public QueueIterator(Queue q) {
        this.list = q.list;
    }

    @Override
    public boolean hasNext() {
        return !(list.isEmpty());
    }

    @Override
    public String next() {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        String res = (String) list.getFirst();
        this.list = this.list.removeFirst();
        return res;
    }
}