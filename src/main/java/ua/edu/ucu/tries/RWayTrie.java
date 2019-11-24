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
        return wordsWithPrefix(s, -1);
    }
    private class NodeTuple{
        public Node node;
        public String word;
		public NodeTuple(Node node, String word) {
			this.node = node;
			this.word = word;
		}
        
    }
    // private void addNodesToQueue(Node cur_node, String prefix, Queue q){
    //     if (cur_node == null){return;}
    //     Queue nodes = new Queue();
    //     nodes.enqueue(new NodeTuple(cur_node, prefix));
    //     while(!(nodes.isEmpty())){
    //         NodeTuple tup = (NodeTuple) nodes.dequue();
    //         if(tup.node.val!=0){
    //             q.enqueue(tup.word);
    //         }
    //         for (int i = 0; i < 26; i++) {
    //             if(tup.node.children[i] != null){
    //                 nodes.enqueue(new NodeTuple(tup.node.children[i], tup.word+getCharFromIndex(i)));
    //             }
    //         }
    //     }
    // }
    @Override
    public Iterable<String> wordsWithPrefix(String s, int k) {
        Node starting_node = getNode(s);
        Queue q = new Queue();
        addNodesToQueue(starting_node, s, q, k);
        return q;
    }
    
    private void addNodesToQueue(Node cur_node, String prefix, Queue q, int k){
        if (cur_node == null){return;}
        Queue nodes = new Queue();
        nodes.enqueue(new NodeTuple(cur_node, prefix));
        int s = 0;
        while(!nodes.isEmpty()){
            NodeTuple tup = (NodeTuple) nodes.dequue();
            if(tup.node.val!=0){
                if(tup.word.length()>s){
                    k-=1;
                    s = tup.word.length();
                    if(k==-1){
                        return;
                    }
                }
                q.enqueue(tup.word);
            }
            for (int i = 0; i < 26; i++) {
                if(tup.node.children[i] != null){
                    nodes.enqueue(new NodeTuple(tup.node.children[i], tup.word+getCharFromIndex(i)));
                }
            }
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
