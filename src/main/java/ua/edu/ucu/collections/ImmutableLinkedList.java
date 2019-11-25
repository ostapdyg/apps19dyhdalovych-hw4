package ua.edu.ucu.collections;

public class ImmutableLinkedList extends LinkedList implements ImmutableList {

    public ImmutableLinkedList() {
        super();
    }

    public ImmutableLinkedList(MutableLinkedList mut) {
        /// Makes ImmutableLinkedList from Mutable; clears mutable list
        this();
        this.size = mut.size();
        this.first = mut.getFirstNode();
        this.last = mut.getLastNode();
        mut.clear();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return new ImmutableLinkedList(
                new MutableLinkedList(new MutableLinkedList(e), new MutableLinkedList(0, this.size(), this)));
    }

    public ImmutableLinkedList addLast(Object e) {
        return new ImmutableLinkedList(
                new MutableLinkedList(new MutableLinkedList(0, this.size(), this), new MutableLinkedList(e)));
    }

    public ImmutableLinkedList removeFirst() {
        return new ImmutableLinkedList(new MutableLinkedList(1, this.size(), this));
    }

    public ImmutableLinkedList removeLast() {
        return new ImmutableLinkedList(new MutableLinkedList(0, this.size() - 1, this));
    }

    public ImmutableLinkedList add(Object e) {
        return this.addLast(e);
    }

    public ImmutableLinkedList add(int index, Object e) {
        return new ImmutableLinkedList(new MutableLinkedList(
                new MutableLinkedList(new MutableLinkedList(0, index, this), new MutableLinkedList(e)),
                new MutableLinkedList(index, this.getSize(), this)));
    }

    public ImmutableLinkedList addAll(Object[] c) {
        return new ImmutableLinkedList(new MutableLinkedList(new MutableLinkedList(0, this.size(), this),
                new MutableLinkedList(0, c.length, c)));
    }

    public ImmutableLinkedList addAll(int index, Object[] c) {
        return new ImmutableLinkedList(new MutableLinkedList(
                new MutableLinkedList(new MutableLinkedList(0, index, this), new MutableLinkedList(0, c.length, c)),
                new MutableLinkedList(index, this.size(), this)));
    }

    public ImmutableLinkedList remove(int index) {
        return new ImmutableLinkedList(new MutableLinkedList(new MutableLinkedList(0, index, this),
                new MutableLinkedList(index + 1, this.getSize(), this)));
    }

    public ImmutableLinkedList set(int index, Object e) {
        return new ImmutableLinkedList(new MutableLinkedList(0, this.size(), this).setMutable(index, e));
    }

    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

}
