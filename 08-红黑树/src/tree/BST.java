package tree;

import java.util.Comparator;

public class BST<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = compare(element, node.element);
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size--;
        if (node.hasTwoChildren()) { // 度为2
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // 度为1
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else {
                node.parent.left = replacement;
            }
            // 删除节点之后的处理
            afterRemove(replacement);
        } else if (node.parent == null) { // 度为0，并且是根节点
            root = null;
            // 删除节点之后的处理
            afterRemove(node);
        } else { // 度为0
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            // 删除节点之后的处理
            afterRemove(node);
        }
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) node = node.right;
            if (cmp < 0) node = node.left;
        }
        return null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * 数据比较
     *
     * @param e1 第一个数据
     * @param e2 第二个数据
     * @return java.lang.Integer
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 数据非空检查
     *
     * @param element 节点数据
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 添加node之后的调整
     *
     * @param node 添加的节点
     */
    protected void afterAdd(Node<E> node) {

    }

    /**
     * 删除节点之后的调整
     *
     * @param node 被删除节点 或者 用以取代被删除节点的子节点（当被删除节点的度为1）
     */
    protected void afterRemove(Node<E> node) {

    }
}
