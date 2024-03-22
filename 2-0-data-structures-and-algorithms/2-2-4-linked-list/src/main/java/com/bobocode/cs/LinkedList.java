package com.bobocode.cs;


import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    private static class Node<T> {
        T value;
        Node<T> next;

        private Node(T element) {
            this.value = element;
        }

        private Node(T element, Node<T> next) {
            this.value = element;
            this.next = next;
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<>();
        Arrays.stream(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        requireNonNull(element);
        if (isEmpty()) {
            head = tail = new Node<>(element);
        } else {
            tail.next = new Node<>(element);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if ((isEmpty() && index == 0) || index == size) {
            add(element);
        } else {
            Objects.checkIndex(index, size);
            Node<T> prev = head;
            while (index > 1) {
                prev = prev.next;
                index--;
            }
            if (prev == head && index == 0) {
                head = new Node<>(element, head);
            } else {
                prev.next = new Node<>(element, prev.next);
            }
            size++;
        }
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        Node<T> curr = head;
        while (index-- > 0) {
            curr = curr.next;
        }
        curr.value = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> curr = head;
        while (index-- > 0) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Node<T> prev = head;
        while (index > 1) {
            prev = prev.next;
            index--;
        }
        Node<T> deletedNode = null;
        if (prev == head && index == 0) {
            deletedNode = head;
            head = head.next;
        } else {
            deletedNode = prev.next;
            prev.next = prev.next.next;
            tail = prev;
        }
        size--;
        return deletedNode.value;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> curr = head;
        while (curr != null) {
            if (curr.value.equals(element)) return true;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Reverses the internal structure of the list
     */
    public void reverse() {
        if (size() < 2) return;

        Node<T> temp = tail = head;
        head = head.next;
        tail.next = null;

        while (head != null) {
            Node<T> curr = head;
            head = head.next;
            curr.next = tail;
            tail = curr;
        }

        head = tail;
        tail = temp;
    }
}
