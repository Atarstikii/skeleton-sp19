import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node<K, V> root;

    public BSTMap() {

    }

    private static class Node<K extends Comparable<K>, V> implements Comparable<K> {
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        int size;

        public Node(K key, V value, Node<K,V> left, Node<K,V> right, int size) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;
        }

        @Override
        public int compareTo(K o) {
            return this.key.compareTo(o);
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return Optional.ofNullable(get(root, key)).isPresent();
    }

    @Override
    public V get(K key) {
        Node<K, V> node = get(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node<K,V> get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmpt = key.compareTo(node.key);
        if (cmpt < 0) {
            node = get(node.left, key);
        } else if (cmpt > 0) {
            node = get(node.right, key);
        }
        return node;
    }

    @Override
    public int size() {
        return sizeOf(root);
    }

    private int sizeOf(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            node = new Node<>(key, value, null, null, 1);
        }
        int cmpt = key.compareTo(node.key);
        if (cmpt < 0) {
            node.left = put(node.left, key, value);
        } else if (cmpt > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.size = sizeOf(node.left) + sizeOf(node.right) + 1;
        return node;
    }

    @Override
    public Set<K> keySet() {
        return keySet(root);
    }

    private Set<K> keySet(Node<K, V> node) {
        Set<K> set = new HashSet<>();
        return keySet(node, set);
    }

    private Set<K> keySet(Node<K, V> node, Set<K> set) {
        if (node == null) {
            return null;
        }
        keySet(node.left, set);
        set.add(node.key);
        keySet(node.right, set);
        return set;
    }

    private K min() {
        return min(root).key;
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private K max() {
        return max(root).key;
    }

    private Node<K, V> max(Node<K, V> node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    /**
     * ■ Save a link to the node to be deleted in t .
     * ■ Set x to point to its successor min(t.right) .
     * ■ Set the right link of x (which is supposed to
     * point to the BST containing all the keys larger
     * than x.key ) to deleteMin(t.right) , the link
     * to the BST containing all the keys that are larger
     * than x.key after the deletion.
     * ■ Set the left link of x (which was null) to t.left
     * (all the keys that are less than both the deleted
     * key and its successor).
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        root = delete(root, key);
        return value;
    }

    private Node<K, V> delete(Node<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        int cmpt = key.compareTo(x.key);
        if (cmpt < 0) {
            x.left = delete(x.left, key);
        } else if (cmpt > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node<K, V> t = x;
            x = min(t.right);
            x.right = delMin(t.right);
            x.left = t.left;
        }
        x.size = sizeOf(x.left) + sizeOf(x.right) + 1;
        return x;
    }

    private Node<K, V> delMin(Node<K, V> x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = delMin(x.left);
        x.size = sizeOf(x.left) + sizeOf(x.right) + 1;
        return x;
    }

    @Override
    public V remove(K key, V value) {
        V actual = get(key);
        if (!Objects.equals(value, actual)) {
            throw new IllegalArgumentException("Value not equal");
        }
        root = delete(root, key);
        return actual;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public void printInOrder() {
        print(root);
    }

    public void print(Node<K, V> node) {
        if (node == null) {
            return;
        }
        print(node.left);
        System.out.println(node.key);
        print(node.right);
    }
}
