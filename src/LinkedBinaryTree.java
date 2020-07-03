/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            parent = p;
            left = l;
            right = r;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public E getElement() throws IllegalStateException {
            return element;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public void setParent(Node<E> p) {
            this.parent = p;
        }

        public void setElement(E data) {
            element = data;
        }

        public String toString() {
            return element.toString();
        }
    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    // LinkedBinaryTree instance variables
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null;     // root of the tree

    /**
     * The number of nodes in the binary tree
     */
    private int size = 0;              // number of nodes in the tree

    private final DefaultComparator<E> comparator = new DefaultComparator<>();

    protected int compareTo(E first, E second) {
        return comparator.compare(first, second);
    }

    // constructor

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    }      // constructs an empty binary tree

    // nonpublic utility

    /**
     * Verifies that a Position belongs to the appropriate class, and is
     * not one that has been previously removed. Note that our current
     * implementation does not actually verify that the position belongs
     * to this particular list instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;       // safe cast
        if (node.getParent() == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node) p).getRight();
    }

    // update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (root != null) throw new IllegalStateException("Tree already has a root.");
        root = createNode(e, null, null, null);
        size++;
        return root;
    }


    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        ((Node<E>) p).setLeft(createNode(e, (Node<E>) p, null, null));
        size++;
        return ((Node<E>) p).getLeft();
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e)
            throws IllegalArgumentException {
        ((Node<E>) p).setRight(createNode(e, (Node<E>) p, null, null));
        size++;
        return ((Node<E>) p).getRight();
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        E old = p.getElement();
        ((Node<E>) p).setElement(e);
        return old;
    }

    /**
     * Removes the node at Position p
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("Can't remove node with 2 children");

        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null) child.setParent(node.getParent());

        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) parent.setLeft(child);
            else parent.setRight(child);
        }

        size--;
        E data = node.getElement();
        node.setParent(node);
        return data;
    }

    public Position<E> createLevelOrder(E[] arr) {
        root = createLevelOrder(arr, null, 0);
        size = arr.length;
        return root;
    }

    private Node<E> createLevelOrder(E[] arr, Node<E> parent, int i) {
        if (i < arr.length) {
            Node<E> node  = createNode(arr[i], parent, null, null);
            Node<E> left = createLevelOrder(arr, node, i * 2 + 1);
            Node<E> right = createLevelOrder(arr, node, i * 2 + 2);
            node.setLeft(left);
            node.setRight(right);
            return node;
         }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Position<E> p : positions()) {
            sb.append(p.getElement());
            sb.append(", ");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append("]");
        return sb.toString();
    }
}

