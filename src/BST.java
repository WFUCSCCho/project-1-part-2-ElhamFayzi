/******************************************************************
 * @file :                     BST.java
 * @description:               This class is a generic implementation of a Binary Search Tree (BST) that stores elements of any type.
 *                             It supports standard operations such as insert, remove, search, clear, and size. This class also implements
 *                             Iterable and provides an in-order iterator that traverses the tree in-order using a stack.
 * @author:                    Elham Fayzi
 * @date:                      Sep 17, 2025
 ******************************************************************/

import java.util.Iterator;
import java.util.Stack;

public class BST<E extends Comparable<E>> implements Iterable<E>{
    private Node<E> root;
    private int nodeCount;

    // Constructor
    public BST() { root = null; nodeCount = 0; }

    // Clears the tree
    public void clear() { root = null; nodeCount = 0; }

    // Returns the size of the tree
    public int size() { return nodeCount; }

    // Inserts a key into the tree
    public void insert(E key) {
        root = insertHelp(root, key);
        nodeCount++;
    }

    // Helper method for inserting
    private Node<E> insertHelp(Node<E> rt, E key) {
        if (rt == null) { return new Node<E>(key); }
        if (rt.value().compareTo(key) > 0) {
            rt.setLeft(insertHelp(rt.getLeft(), key));
        }
        else if (rt.value().compareTo(key) < 0){
            rt.setRight(insertHelp(rt.getRight(), key));
        }
        return rt;
    }

    // Removes a key from the tree if it exists and returns it
    public E remove(E key) {
        E temp = searchHelp(root, key);
        if (temp != null) {
            root = removeHelp(root, key);
            nodeCount--;
        }
        return temp;
    }

    // Helper method for removal
    private Node<E> removeHelp(Node<E> rt, E key) {
        if (rt == null) { return null; }
        if (rt.value().compareTo(key) > 0) {
            rt.setLeft(removeHelp(rt.getLeft(), key));
        }
        else if (rt.value().compareTo(key) < 0) {
            rt.setRight(removeHelp(rt.getRight(), key));
        }
        else {
            if (rt.getLeft() == null) { return rt.getRight(); }
            else if (rt.getRight() == null) { return rt.getLeft(); }
            else {
                Node<E> temp = getMin(rt.getRight());
                rt.setValue(temp.value());
                rt.setRight(deleteMin(rt.getRight()));
            }
        }

        return rt;
    }

    // Finds the minimum value node in a subtree
    private Node<E> getMin(Node<E> rt) {
        if (rt == null) { return null; }
        if (rt.getLeft() == null) { return rt; }
        return getMin(rt.getLeft());
    }

    // Deletes the minimum node in a subtree
    private Node<E> deleteMin(Node<E> rt) {
        if (rt == null) { return null; }
        if (rt.getLeft() == null) { return rt.getRight(); }

        rt.setLeft(deleteMin(rt.getLeft()));

        return rt;
    }

    // Finds and returns a key or null if not found
    public E search(E key) { return searchHelp(root, key); }

    // Helper method for searching
    private E searchHelp(Node<E> rt, E key) {
        if (rt == null) { return null; }
        if (rt.value().compareTo(key) == 0) { return rt.value(); }
        if (rt.value().compareTo(key) > 0) { return searchHelp(rt.getLeft(), key); }
        else { return searchHelp(rt.getRight(), key); }
    }

    // Returns an iterator for in-order traversal
    public Iterator<E> iterator() {
        return new BSTIterator();
    }

    //Inner class for BST Iterator used for in-order BST traversal
    private class BSTIterator implements Iterator<E> {
        private Stack<Node<E>> stack;

        // Constructor
        public BSTIterator() {
            if (root != null) {
                stack = new Stack<Node<E>>();
                goLeftFrom(root);
            }
        }

        //push all left children onto stack
        private void goLeftFrom(Node<E> n) {
            while (n != null) {
                stack.push(n);
                n = n.getLeft();
            }
        }

        // Check if there are more nodes to visit
        @Override
        public boolean hasNext() {
            return (!stack.isEmpty());
        }

        // Return the next node in-order
        @Override
        public E next() {
            if (!hasNext()) {
                return null;
            }

            Node<E> n = stack.pop();
            E res = n.value();

            if (n.getRight() != null) {
                goLeftFrom(n.getRight());
            }

            return res;
        }
    }
}