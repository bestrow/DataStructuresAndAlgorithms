package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> {

    protected int size;
    protected Node<E> root;


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    /**
     * 递归 前序遍历
     *
     * @param node 节点
     */
    private void preorderTraversal(Node<E> node) {
        if (node == null) return;
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 通过栈进行前序遍历
     *
     * @param node 节点
     */
    private void preorderTraversalWithStack(Node<E> node) {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> treeNode = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                System.out.println(treeNode.element);
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 递归 前序遍历 增强
     */
    public void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }

    /**
     * 递归 前序遍历 增强
     *
     * @param node 节点
     */
    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    /**
     * 递归 中序遍历
     *
     * @param node 节点
     */
    private void inorderTraversal(Node<E> node) {
        if (node == null) return;
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    /**
     * 通过栈进行中序遍历
     *
     * @param node 节点
     */
    private void inorderTraversalWithStack(Node<E> node) {
        Stack<Node<E>> stack = new Stack<>();
        Node<E> treeNode = node;
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                System.out.println(treeNode.element);
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 递归 中序遍历 增强
     */
    public void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }

    /**
     * 递归 中序遍历 增强
     *
     * @param node 节点
     */
    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * 递归 后序遍历
     *
     * @param node 节点
     */
    private void postOrderTraversal(Node<E> node) {
        if (node == null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * 递归 后序遍历 增强
     */
    public void postOrder(Visitor<E> visitor) {
        if (visitor == null) return;
        postOrder(root, visitor);
    }

    /**
     * 递归 后序遍历 增强
     *
     * @param node 节点
     */
    private void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;

        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 通过队列进行层序遍历
     */
    public void levelOrderTraversalWithQueue() {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> treeNode = queue.poll();
            System.out.println(treeNode.element);
            if (treeNode.left != null) queue.offer(treeNode.left);
            if (treeNode.right != null) queue.offer(treeNode.right);
        }
    }

    /**
     * 通过队列进行层序遍历 增强
     */
    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> treeNode = queue.poll();
            if (visitor.visit(treeNode.element)) return;
            if (treeNode.left != null) queue.offer(treeNode.left);
            if (treeNode.right != null) queue.offer(treeNode.right);
        }
    }

    /**
     * 二叉树高度
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 递归求高度
     *
     * @param node 起始节点
     * @return
     */
    private int height(Node<E> node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 层序遍历求二叉树高度
     *
     * @return
     */
    public int height2() {
        if (root == null) return 0;
        Queue<Node<E>> queue = new LinkedList<>();
        int levelSize = 1;
        int height = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 判断二叉树是否是完全二叉树
     *
     * @return true(是)，false(否)
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 查找二叉树的前驱
     *
     * @param node 二叉树节点
     * @return 前驱节点
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 查找二叉树的后继
     *
     * @param node 二叉树节点
     * @return 后继节点
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }

        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }

        @Override
        public String toString() {
            String parentStr = null;
            if (this.parent != null) {
                parentStr = this.parent.element.toString();
            }
            return this.element + "_p(" + parentStr + ")";
        }
    }

    public static abstract class Visitor<E> {
        boolean stop;

        /**
         * @return 当值返回true时，停止
         */
        public abstract boolean visit(E e);
    }
}
