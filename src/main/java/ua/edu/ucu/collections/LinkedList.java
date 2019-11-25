package ua.edu.ucu.collections;

public class LinkedList {
    protected int size;
    protected Node first;
    protected Node last;

    public LinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;

    }

    public Node getNode(int index) {
        if (index >= this.size) {
            System.out.println(index);
            throw new IndexOutOfBoundsException();
        } else if (index <= this.size / 2) {
            Node res = this.first;
            for (int i = 0; i < index; i++) {
                res = res.getNext();
            }
            return res;
        } else {
            Node res = this.last;
            for (int i = this.size - 1; i > index; i--) {
                res = res.getPrev();
            }
            return res;
        }
    }

    public int getSize() {
        return this.size;
    }

    public Node getFirstNode() {
        return this.first;
    }

    public Node getLastNode() {
        return this.last;
    }

    public Object getFirst() {
        return this.getFirstNode().getData();
    }

    public Object getLast() {
        return this.getLastNode().getData();
    }

    public Object get(int index) {
        return this.getNode(index).getData();
    }

    public int indexOf(Object e) {
        Node cur_node = this.getFirstNode();
        for (int i = 0; i < this.size; i++) {
            if (cur_node.getData().equals(e)) {
                return i;
            }
            cur_node = cur_node.getNext();
        }
        return -1;
    }

    public int size() {
        return this.size;
    }

    public Object[] toArray() {
        Object[] res = new Object[this.size];
        Node cur_node = this.getFirstNode();
        for (int i = 0; i < this.size; i++) {
            res[i] = cur_node.getData();
            cur_node = cur_node.getNext();
        }
        return res;
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(3 * this.size() - 2);
        Node cur_node = this.getFirstNode();
        for (int i = 0; i < this.size - 1; i++) {
            sb.append(cur_node.getData());
            sb.append(", ");
            cur_node = cur_node.getNext();
        }
        sb.append(cur_node.getData());
        return sb.toString();
    }
}