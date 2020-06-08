package tree;

import java.util.Comparator;

public class AVLTree<E> extends BBST<E> {


    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
        }
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                break;
            }
        }
    }

    /**
     * 恢复平衡
     */
    private void rebalance(Node<E> grandParent) {
        Node<E> parent = ((AVLNode<E>) grandParent).tallChild();
        Node<E> node = ((AVLNode<E>) parent).tallChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grandParent);
            } else { //LR
                rotateLeft(parent);
                rotateRight(grandParent);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grandParent);
            } else { // RR
                rotateLeft(grandParent);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 恢复平衡
     */
    private void rebalance2(Node<E> grandParent) {
        Node<E> parent = ((AVLNode<E>) grandParent).tallChild();
        Node<E> node = ((AVLNode<E>) parent).tallChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grandParent, node.left, node, node.right, parent, parent.right, grandParent, grandParent.right);
            } else { //LR
                rotate(grandParent, parent.left, parent, node.left, node, node.right, grandParent, grandParent.right);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grandParent, grandParent.left, grandParent, node.left, node, node.right, parent, parent.right);
            } else { // RR
                rotate(grandParent, grandParent.left, grandParent, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);

        // 更新高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private static class AVLNode<E> extends Node<E> {

        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            String parentStr = null;
            if (this.parent != null) {
                parentStr = this.parent.element.toString();
            }
            return this.element + "_p(" + parentStr + ")_h(" + height + ")";
        }
    }
}
