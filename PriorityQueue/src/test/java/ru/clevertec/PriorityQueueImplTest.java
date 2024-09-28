package ru.clevertec;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueImplTest {

    private PriorityQueueImpl<Integer> priorityQueue;

    @BeforeEach
    public void setUp() {
        priorityQueue = new PriorityQueueImpl<>();
    }

    @Test
    public void shouldAddAndPeekElement() {
        priorityQueue.add(8);
        priorityQueue.add(25);
        priorityQueue.add(4);
        priorityQueue.add(7);
        assertEquals(4, priorityQueue.peek());
    }

    @Test
    public void shouldAddAndPollElement() {
        priorityQueue.add(8);
        priorityQueue.add(25);
        priorityQueue.add(4);
        priorityQueue.add(7);
        assertEquals(4, priorityQueue.poll());
    }

    @Test
    public void shouldNotReturnElementPollEmpty_thenException() {
        assertThrows(IllegalStateException.class, () -> { priorityQueue.poll(); });
    }

    @Test
    public void shouldNotReturnElementPeekEmpty_thenException() {
        assertThrows(IllegalStateException.class, () -> { priorityQueue.peek(); });
    }

    @Test
    public void shouldReturnExceptionWhenAddNull() {
        assertThrows(IllegalArgumentException.class, () -> { priorityQueue.add(null); });
    }

    @Test
    public void addElementWithCustomComparator() {
        PriorityQueue<Integer> customPriorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        customPriorityQueue.add(10);
        customPriorityQueue.add(5);
        customPriorityQueue.add(20);
        assertEquals(20, customPriorityQueue.poll());
        assertEquals(10, customPriorityQueue.peek());
    }
}