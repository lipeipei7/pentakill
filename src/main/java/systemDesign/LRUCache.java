package systemDesign;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/*
    205. Implement LRU Cache
    Implement a least recently used cache.
    It should provide set(), get() operations.
    If not exists, return null (Java), false (C++), -1(Python).
*/
class LRUCache<K, V> {

    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> prev;
        public Node<K, V> next;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<K, Node<K, V>> keyMap;
    private int capacity;

    @Deprecated
    private DoublyList doublyList;
    private Deque<Node<K, V>> deque;

    public LRUCache(int capacity) {
        keyMap = new HashMap<>();
        deque = new LinkedList<>();
        this.capacity = capacity;
    }

    public V get(K key) {
        if (!keyMap.containsKey(key)) {
            return null;
        }
        Node<K, V> node = keyMap.get(key);
        deque.remove(node);
        deque.offerLast(node);
        return node.value;
    }

    public void set(K key, V value) {
        //update
        if (keyMap.containsKey(key)) {
            Node<K, V> node = keyMap.get(key);
            node.value = value;
            deque.remove(node);
            deque.offerLast(node);
        }
        //insert at capacity
        else if (keyMap.size() == capacity) {
            Node<K, V> node = new Node(key, value);
            keyMap.put(key, node);
            deque.offerLast(node);
            Node<K, V> head = deque.pollFirst();
            keyMap.remove(head.key);
        }
        // insert
        else {
            Node<K, V> node = new Node(key, value);
            keyMap.put(key, node);
            deque.offerLast(node);
        }
    }

    public static class DoublyList {
        public Node head;
        public Node tail;

        public void add(Node node) {
            if (tail == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
        }

        public void remove(Node node) {
            if (head == tail && head == node) {
                head = null;
                tail = null;
            } else if (head == node) {
                head = node.next;
                node.next.prev = null;
            } else if (tail == node) {
                tail = node.prev;
                node.prev.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }
    }
}

/*
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
