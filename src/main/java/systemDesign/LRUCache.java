package systemDesign;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private Map<Integer, DoublyList.Node> map;
    private DoublyList doublyList;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        doublyList = new DoublyList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        DoublyList.Node node = map.get(key);
        doublyList.remove(node);
        doublyList.add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoublyList.Node node = map.get(key);
            node.value = value;
            doublyList.remove(node);
            doublyList.add(node);
        } else if (map.size() == capacity) {
            DoublyList.Node node = new DoublyList.Node(key, value);
            map.put(key, node);
            doublyList.add(node);
            map.remove(doublyList.head.key);
            doublyList.remove(doublyList.head);
        } else {
            DoublyList.Node node = new DoublyList.Node(key, value);
            map.put(key, node);
            doublyList.add(node);
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
            if (head == tail) {
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

        public static class Node {
            public int key;
            public int value;
            public Node prev;
            public Node next;
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
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
