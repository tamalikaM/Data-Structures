    package Trees;
    import java.util.*;

    public class BinarySearchTreeOperations<E extends Comparable<? super E>> {

        private TreeNode<E> root;
        public boolean useRecursion = true;
        private TreeNode<E> parent, startNode;

        public BinarySearchTreeOperations() {
            root = null;
            parent = null;
        }

        private void preorderTraversal(TreeNode<E> node) {
            if(node != null) {
                visited(node);
                preorderTraversal(node.getLeftChild());
                preorderTraversal(node.getRightChild());
            }
        }

        public void printPreorder(){
            if(root != null)
                preorderTraversal(root);
            else
                System.out.println("Empty Tree");
            System.out.println();
        }

        private void inorderTraversal(TreeNode<E> node) {
            if(node != null) {
                inorderTraversal(node.getLeftChild());
                visited(node);
                inorderTraversal(node.getRightChild());
            }
        }

        public void printInorder(){
            if(root != null)
                inorderTraversal(root);
            else
                System.out.println("Empty Tree");
            System.out.println();
        }

        private void postorderTraversal(TreeNode<E> node) {
            if(node != null) {
                postorderTraversal(node.getLeftChild());
                postorderTraversal(node.getRightChild());
                visited(node);
            }
        }

        public void printPostorder(){
            if(root != null)
                postorderTraversal(root);
            else
                System.out.println("Empty Tree");
            System.out.println();
        }

        public void printTree(){
            if(root != null)
                BFS(root);
            else
                System.out.println("Empty Tree");
            System.out.println();
        }

        private void BFS(TreeNode<E> node) {
            List<TreeNode<E>> myQueue;
            myQueue = new LinkedList<>();
            myQueue.add(node);
            while (!myQueue.isEmpty()) {
                if(node.getLeftChild() != null)
                    myQueue.add(node.getLeftChild());
                if(node.getRightChild() != null)
                    myQueue.add(node.getRightChild());
                visited(node);
                myQueue.remove(0);
                if(!myQueue.isEmpty())
                    node = myQueue.get(0);
            }
        }

        private void visited(TreeNode<E> node) {
            if(node.getData() != null)
                System.out.print(node.getData() + "  ");
            else
                throw new NullPointerException();
        }

        private void setRoot(E element){
            root = new TreeNode<>(element);
        }
        private void setStartNode(E element){
            startNode = new TreeNode<>(element);
        }

        private TreeNode<E> toFindRecursive(E data, TreeNode<E> node) {
            if (node == null)
                return null;
            int comp = data.compareTo(node.getData());
            if(comp > 0){
                parent = node;
                return toFindRecursive(data, node.getRightChild());
            }
            else if(comp < 0 ) {
                parent = node;
                return toFindRecursive(data, node.getLeftChild());
            }
            return node;
        }

        public boolean find(E data) {
            if(data == null)
                throw new NullPointerException();
            if(root == null) {
                System.out.println("Empty Tree");
                return false;
            }
            startNode = root;
            parent = root;
            if(useRecursion)
                return toFindRecursive(data, startNode) != null;
            return toFindIterative(data, startNode) != null;
        }

        private TreeNode<E> toFindIterative(E data, TreeNode<E> startNode) {
            TreeNode<E> curr = startNode;
            int comp;
            while(curr != null){
                comp = data.compareTo(curr.getData());
                if(comp > 0){
                    parent = curr;
                    curr = curr.getRightChild();
                }
                else if(comp < 0) {
                    parent = curr;
                    curr = curr.getLeftChild();
                }
                else
                    return curr;
            }
           return null;
        }

        public boolean insert(E element) {
            if(element == null)
                throw new NullPointerException();
            if(root == null){
                setRoot(element);
                return true;
            }
            startNode = root;
            return insertIterative(element, startNode);
        }

        private boolean insertIterative(E data, TreeNode<E> startNode) {

            TreeNode<E> curr = startNode;
            int comp = data.compareTo(curr.getData());
            while(comp < 0 && curr.getLeftChild() != null || comp > 0 && curr.getRightChild() != null){
                if(comp > 0)
                    curr = curr.getRightChild();
                else
                    curr = curr.getLeftChild();
                comp = data.compareTo(curr.getData());
            }
            if(comp > 0)
                curr.setRightChildData(data);
            else if(comp < 0)
                curr.setLeftChildData(data);
            else
                return false;
            return true;
        }

        private TreeNode<E> findMin(TreeNode<E> startNode) {
            if(startNode == null)
                return null;
            if(startNode.getLeftChild() != null)
                return findMin(startNode.getLeftChild());
            return startNode;
        }

        public boolean deleteNode(E element){
            startNode = root;
            return deleteNode(element, startNode);
        }

        boolean deleteNode(E element, TreeNode<E> start) {
            TreeNode<E> foundNode = toFindRecursive(element, start);
            if(foundNode == null)
                return false;
            //start deletion
            //no child
            TreeNode<E> temp;
            if(foundNode.getLeftChild() == null && foundNode.getRightChild() == null) {
                if(parent.getLeftChild() == foundNode)
                    parent.setLeftChild(null);
                else if(parent.getRightChild() == foundNode)
                    parent.setRightChild(null);
                else {
                    if(root == foundNode) {
                        root = null;
                        startNode = null;
                        parent = null;
                        System.out.println("case 1.3");
                        return true;
                    }

                }
                System.out.println("case 1");

            }
            //two children
            else if(foundNode.getRightChild() != null && foundNode.getLeftChild() != null){
                temp = findMin(foundNode.getRightChild());
                if(foundNode.getRightChild() == temp)
                    parent = foundNode;
                foundNode.setData(temp.getData());
                System.out.println("case 2");
                deleteNode(temp.getData(),foundNode.getRightChild());
            }
            //one child
            else{
                if(foundNode.getLeftChild() == null) {
                    if(parent.getRightChild() == foundNode){
                        parent.setRightChild(foundNode.getRightChild());
                    }
                    else {
                        parent.setLeftChild(foundNode.getRightChild());
                    }
                    System.out.println("case 3.1");
                }
                else{
                    if(parent.getRightChild() == foundNode){
                        parent.setRightChild(foundNode.getLeftChild());
                    }
                    else
                        parent.setLeftChild(foundNode.getLeftChild());
                    System.out.println("case 3.2");
                }
                System.out.println("case 3");
            }
//            System.out.println("parent" + parent.getData());
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

        public void setRightChildData(E data) {
            if(data == null)
                rightChild = null;
            else
                rightChild = new TreeNode<>(data);
        }
        public void setLeftChildData(E data) {
            if(data == null)
                leftChild = null;
            else
                leftChild = new TreeNode<>(data);
        }

        public void setLeftChild(TreeNode<E> node){
            leftChild = node;
        }
        public void setRightChild(TreeNode<E> node){
            rightChild = node;
        }

        public E getData(){
            return data;
        }
        public void setData(E data) {
            if(data != null)
                this.data = data;
            else
                this.data = null;
        }
    }
