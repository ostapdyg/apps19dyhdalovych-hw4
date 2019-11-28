package ua.edu.ucu.tries;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ua.edu.ucu.collections.Queue;

public class RWayTrie implements Trie {
    private Node root;

    private static class Node {
        private final Node[] children = new Node[26];
        private int val = 0;
        private boolean end;
        private String word;

    }

    private static int getCharIndex(final char c) {
        return (int) c - (int) 'a';
    }

    private static char getCharFromIndex(final int i) {
        return (char) (i + (int) 'a');
    }

    @Override
    public void add(final Tuple t) {
        root = add(root, t, 0);
    }

    private Node add(Node cur_node, final Tuple t, final int index) {
        if (cur_node == null) {
            cur_node = new Node();
        }
        if (index == t.term.length()) {
            cur_node.val = t.weight;
            cur_node.end = true;
            cur_node.word = t.term;
            return cur_node;
        }
        final int i = getCharIndex(t.term.charAt(index));
        cur_node.children[i] = add(cur_node.children[i], t, index + 1);
        return cur_node;
    }

    @Override
    public boolean contains(final String word) {
        return contains(root, word, 0);
    }

    private boolean contains(final Node cur_node, final String word, final int index) {
        if (index == word.length()) {
            return true;
        }
        if (cur_node == null) {
            return false;
        }
        final int i = getCharIndex(word.charAt(index));
        return contains(cur_node.children[i], word, index + 1);
    }

    private Node getNode(final String prefix) {
        return getNode(root, prefix, 0);
    }

    private Node getNode(final Node cur_node, final String prefix, final int index) {
        if (prefix.length() == index) {
            return cur_node;
        }
        if (cur_node == null) {
            return null;
        }
        final int i = getCharIndex(prefix.charAt(index));
        return getNode(cur_node.children[i], prefix, index + 1);
    }

    @Override
    public boolean delete(final String word) {
        final Node n = getNode(word);
        if (n == null) {
            return false;
        }
        n.val = 0;
        return true;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(final String s) {
        return wordsWithPrefix(s, -1);
    }

    private static class NodeTuple {
        public Node node;
        public String word;

        public NodeTuple(final Node node, final String word) {
            this.node = node;
            this.word = word;
        }

    }

    // private void addNodesToQueue(Node cur_node, String prefix, Queue q){
    // if (cur_node == null){return;}
    // Queue nodes = new Queue();
    // nodes.enqueue(new NodeTuple(cur_node, prefix));
    // while(!(nodes.isEmpty())){
    // NodeTuple tup = (NodeTuple) nodes.dequue();
    // if(tup.node.val!=0){
    // q.enqueue(tup.word);
    // }
    // for (int i = 0; i < 26; i++) {
    // if(tup.node.children[i] != null){
    // nodes.enqueue(new NodeTuple(tup.node.children[i],
    // tup.word+getCharFromIndex(i)));
    // }
    // }
    // }
    // }

    private static class RWayTrieIterator implements Iterator<String> {
        private int num_lengths = 0;
        private String word_to_return;
        private Queue nodes_to_visit;
        private int prev_l = 0;

        public RWayTrieIterator(Node current_node, int k) {
            this.num_lengths = k+1;
            this.nodes_to_visit = new Queue();
            this.nodes_to_visit.enqueue(current_node);
            word_to_return = changeWordToReturn();
        }

        @Override
        public boolean hasNext() {
            return !(word_to_return==null);
        }

        @Override
        public String next() {
            if (word_to_return == null) {
                throw new NoSuchElementException();
            }
            String res = word_to_return;
            word_to_return = changeWordToReturn();
            return res;
        }

        private String changeWordToReturn() {
            while (!nodes_to_visit.isEmpty()) {
                Node cur_node = (Node) nodes_to_visit.dequeue();
                for (Node next_node : cur_node.children) {
                    if (!(next_node == null)) {
                        nodes_to_visit.enqueue(next_node);
                    }
                }
                if (cur_node.end) {
                    if (cur_node.word.length() > prev_l) {
                        prev_l = cur_node.word.length();
                        num_lengths -= 1;
                        if (num_lengths == 0) {
                            return null;
                        }
                    }
                    return cur_node.word;
                }
            }
            return null;
        }
    }

    @Override
    public Iterable<String> wordsWithPrefix(final String s, final int k) {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new RWayTrieIterator(getNode(s), k);
                };
            };
        }

    @Override
    public int size() {
        return size(root);
    }

    private int size(final Node cur_node) {
        if (cur_node == null) {
            return 0;
        }
        int s = 0;
        if (cur_node.val != 0) {
            s += 1;
        }
        for (int i = 0; i < 26; i++) {
            s += size(cur_node.children[i]);
        }
        return s;
    }

}
