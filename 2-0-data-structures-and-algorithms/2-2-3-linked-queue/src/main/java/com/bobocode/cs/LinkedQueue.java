package com.bobocode.cs;

import static java.util.Objects.requireNonNull;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        private Node() {
        } // no-args constructor is needed for reflection in tests

        private Node(T element, Node<T> prev, Node<T> next) {
            this.value = element;
            this.prev = prev;
            this.next = next;
        }
    }


    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        requireNonNull(element);

        if (head == null) {
            head = tail = new Node<>(element, null, null);
        } else {
            tail = new Node<>(element, tail, null);
            tail.prev.next = tail;
        }
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        if (head == null) {
            return null;
        }

        T element = head.value;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return element;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
