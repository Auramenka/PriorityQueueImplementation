package ru.clevertec;

import java.util.Arrays;
import java.util.Comparator;


public class PriorityQueueImpl<T> {

    private static final int INITIAL_CAPACITY = 8;
    private T[] elements;
    private int size;
    private Comparator<? super T> comparator;

    public PriorityQueueImpl() {
        this(INITIAL_CAPACITY, null);
    }

    public PriorityQueueImpl(Comparator<? super T> comparator) {
        this(INITIAL_CAPACITY, comparator);
    }

    @SuppressWarnings("unchecked")
    public PriorityQueueImpl(int initialCapacity, Comparator<? super T> comparator) {
        this.elements = (T[]) new Object[initialCapacity];
        this.size = 0;
        this.comparator = comparator;
    }

    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element can't be null");
        }

        if (size >= elements.length) {
            resize();
        }
        elements[size] = element;
        size++;
        siftUp(size - 1);
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty");
        }
        return elements[0];
    }

    public T poll() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty");
        }
        T minElement = elements[0];
        elements[0] = elements[size - 1];
        size--;
        siftDown(0);
        return minElement;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(elements[index], elements[parentIndex]) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void siftDown(int index) {
        while (index < size) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallestIndex = index;

            if (leftChildIndex < size && compare(elements[leftChildIndex], elements[smallestIndex]) < 0) {
                smallestIndex = leftChildIndex;
            }

            if (rightChildIndex < size && compare(elements[rightChildIndex], elements[smallestIndex]) < 0) {
                smallestIndex = rightChildIndex;
            }

            if (smallestIndex == index) {
                break;
            }

            swap(index, smallestIndex);
            index = smallestIndex;
        }
    }

    private int compare(T t1, T t2) {
        if (comparator != null) {
            return comparator.compare(t1, t2);
        } else if (t1 instanceof Comparable) {
            return ((Comparable<T>) t1).compareTo(t2);
        } else {
            throw new IllegalArgumentException("Elements must implement Comparable or a Comparator must be provided");
        }
    }

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    private void swap(int index1, int index2) {
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }
}
