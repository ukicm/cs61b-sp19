import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MyTrieSet implements TrieSet61B {
    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> map;

        Node(boolean b) {
            this.isKey = b;
            map = new HashMap<>();
        }
    }

    private Node root;

    public MyTrieSet() {
        root = new Node(false);
    }

    @Override
    /** Clears all items out of Trie */
    public void clear() {
        root = new Node(false);
    }

    @Override
    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            throw new IllegalArgumentException();
        }
        Node n = find(key);
        return n != null && n.isKey;
    }

    private Node find(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i += 1) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }

    @Override
    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> keys = new ArrayList<>();
        Node n = find(prefix);
        collect(prefix, keys, n);
        return keys;
    }

    private void collect(String s, List<String> x, Node n) {
        if (n == null) {
            return;
        }
        if (n.isKey) {
            x.add(s);
        }
        for (char c : n.map.keySet()) {
            collect(s + c, x, n.map.get(c));
        }
    }

    @Override
    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet t = new MyTrieSet();
        t.add("hello");
        t.add("hi");
        t.add("help");
        t.add("zebra");
    }
}
