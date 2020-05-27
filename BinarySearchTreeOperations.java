package Trees;

import java.util.*;

public class BinarySearchTreeOperations<E extends Comparable<? super E>> {

    private TreeNode<E> root;

    public BinarySearchTreeOperations() {
        TreeNode<E> root = null;
    }

    public void printPreOrder(TreeNode<E> node) {
        if(node != null) {
            visited(node);
            printPreOrder(node.getLeftChild());
            printPreOrder(node.getRightChild());
        }
    }

    public void printInOrder(TreeNode<E> node) {
        if(node != null) {
            printInOrder(node.getLeftChild());
            visited(node);
            printInOrder(node.getRightChild());
        }
    }

    public void printPostOrder(TreeNode<E> node) {
        if(node != null) {
            printPostOrder(node.getLeftChild());
            printPostOrder(node.getRightChild());
            visited(node);
        }
    }

    private void BFS(TreeNode<E> node) {
        if(node == null)
             throw new NullPointerException();
        List<TreeNode<E>> myQueue;
        myQueue = new LinkedList<TreeNode<E>>();
        myQueue.add(node);

        while (!myQueue.isEmpty()) {
            visited(myQueue.get(0));
            myQueue.remove(0);
            if(node.getLeftChild() != null)
                myQueue.add(node.getLeftChild());
            if(node.getRightChild() != null)
                myQueue.add(node.getRightChild());
        }
    }

    private void visited(TreeNode<E> node) {
        if(node.getData() != null)
            System.out.print(node.getData());
        else
            throw new NullPointerException();
    }

    private boolean toFindRecursive(E data, TreeNode<E> node) {
        if(data == null)
            throw new NullPointerException();
        if (node == null)
            return false;

            int comp = data.compareTo(node.getData());
            if(comp > 0){
                return toFindRecursive(data, node.getRightChild());
            }
            else if(comp < 0 ) {
                return toFindRecursive(data, node.getLeftChild());
            }
            return true;
    }

    public boolean find(E data) {
        return toFindRecursive(data, root);
    }

    private boolean toFindIterative(E data) {
        if(data == null) {
            throw new NullPointerException();
        }
        else if(root == null)
            return false;
        TreeNode<E> curr = root;
        int comp;
        while(curr != null){
            comp = data.compareTo(curr.getData());
            if(comp > 0)
                curr = curr.getRightChild();
            else if(comp < 0)
                curr = curr.getLeftChild();
            return true;
        }
       return false;
    }
    private boolean insertIterative(E data) {
        if(data == null) {
            throw new NullPointerException();
        }

        TreeNode<E> curr = root;
        int comp = data.compareTo(curr.getData());;
        while(comp < 0 && curr.getLeftChild() != null || comp > 0 && curr.getRightChild() != null){
            comp = data.compareTo(curr.getData());
            if(comp > 0)
                curr = curr.getRightChild();
            else
                curr = curr.getLeftChild();
        }
        comp = data.compareTo(curr.getData());
        if(comp > 0)
            curr.setRightChild(data);
        else if(comp > 0)
            curr.setLeftChild(data);
        else
            return false;
        return true;
    }
}

class  TreeNode<E>  {
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;
    private E data;

    public TreeNode (E data){
        this.data = data;
    }

    public TreeNode<E> getLeftChild(){
        return leftChild;
    }

    public TreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(E data) {
        rightChild.data = data;
    }
    public void setLeftChild(E data) {
        leftChild.data = data;
    }
    public E getData(){
        return data;
    }
}
