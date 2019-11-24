package ua.edu.ucu.tries;
import ua.edu.ucu.collections.Queue;

public class RWayTrie implements Trie {
    private Node root;

    private class Node{
        private Node[] children = new Node[26];
        private int val=0;

    }
    private static int getCharIndex(char c){
        return (int) c - (int) 'a';
    }
    private static char getCharFromIndex(int i){
        return (char)(i+(int)'a');
    }

    @Override
    public void add(Tuple t) {
        root = add(root, t, 0);
    }

    private Node add(Node cur_node, Tuple t, int index){
        if(cur_node == null){cur_node = new Node();}
        if(index == t.term.length()){
            cur_node.val = t.weight;
            return cur_node;
        }
        int i = getCharIndex(t.term.charAt(index));
        cur_node.children[i] = add(cur_node.children[i], t, index+1);
        return cur_node;
    }

    @Override
    public boolean contains(String word) {
        return contains(root, word, 0);
    }
    private boolean contains(Node cur_node, String word, int index){
        if(index == word.length()){return true;}
        if(cur_node==null){return false;}
        int i = getCharIndex(word.charAt(index));
        return contains(cur_node.children[i], word, index+1);
    }

    private Node getNode(String prefix){
        return getNode(root, prefix, 0);
    }

    private Node getNode(Node cur_node, String prefix, int index){
        if(prefix.length() == index){
            return cur_node;
        }
        if(cur_node ==null){return null;}
        int i = getCharIndex(prefix.charAt(index));
        return getNode(cur_node.children[i], prefix, index+1);
    }


    @Override
    public boolean delete(String word) {
        Node n = getNode(word);
        if(n == null){return false;}
        n.val = 0;
        return true;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node starting_node = getNode(s);
        Queue q = new Queue();
        addNodesToQueue(starting_node, s, q);
        return q;
    }
    
    private void addNodesToQueue(Node cur_node, String prefix, Queue q){
        if (cur_node == null){return;}
        if (cur_node.val != 0){q.enqueue(prefix);}
        for (char c = 0; c < 26; c++){
            addNodesToQueue(cur_node.children[c], prefix + getCharFromIndex(c), q);
        }
    }
    public Iterable<String> wordsWithPrefix(String s, int k) {
        Node starting_node = getNode(s);
        Queue q = new Queue();
        addNodesToQueue(starting_node, s, q, k);
        return q;
    }
    
    private void addNodesToQueue(Node cur_node, String prefix, Queue q, int k){
        if (cur_node == null){return;}
        if(k == 0){return;}
        if (cur_node.val != 0){q.enqueue(prefix);}
        for (char c = 0; c < 26; c++){
            addNodesToQueue(cur_node.children[c], prefix + getCharFromIndex(c), q);
        }
    }
    @Override
    public int size() {
        return size(root);
    }


    private int size(Node cur_node){
        if(cur_node == null){return 0;}
        int s = 0;
        if(cur_node.val != 0){s += 1;}
        for(int i=0; i< 26; i++){
            s += size(cur_node.children[i]);
        }
        return s;
    }

}
