package ua.edu.ucu.collections;


public class Queue {
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

    public Object dequeue() {
        Object res = this.peek();
        this.list = this.list.removeFirst();
        return res;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

}

