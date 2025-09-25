/******************************************************************
 * @file :                     Node.java
 * @description:               This class represents a single node in a binary tree structure. Each node stores an element of a generic
 *                             type E (which must be Comparable) along with references to its left and right child nodes. It provides constructors,
 *                             getters and setters for its fields, and a helper method.
 * @author:                    Elham Fayzi
 * @date:                      Sep 17, 2025
 ******************************************************************/

public class Node<E extends Comparable<? super E>> {
    private E element;
    private Node<E> left;
    private Node<E> right;

    // Constructors
    public Node() {
        left = right = null;
    }
    public Node(E val) {
        element = val;
        left = right = null;
    }
    public Node(E val, Node<E> l, Node<E> r) {
        element = val;
        left = l;
        right = r;
    }

    // Get the value stored in this node
    public E value() { return element; }
    // Set a new value for this node
    public void setValue(E val) { element = val; }

    // Get the left child
    public Node<E> getLeft() { return left; }
    // Set the left child
    public void setLeft(Node<E> n) { left = n; }

    // Get the right child
    public Node<E> getRight() { return right; }
    // Set the right child
    public void setRight(Node<E> n) { right = n; }

    // Checks if a node is a leaf node
    public boolean isLeaf() { return (left==null) && (right == null); }
}
