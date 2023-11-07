package dataStructure.priorityQueue;

import java.util.ArrayList;
import java.util.List;

public class LAI25<T extends Comparable<T>> {
    private List<T> heap = new ArrayList<>();

    public void offer(T ele) {
        heap.add(ele);

    }

    public T poll() {
        return null;
    }

    public T peek() {
        return null;
    }

    public void heapifyUp(int index) {

    }

    public void heapifyDown(int index) {

    }
    
    public void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

}
