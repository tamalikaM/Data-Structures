package Trees;

public class BinarySearchTreeTest  {
    public static void main(String[] args){
        BinarySearchTreeOperations<Integer> BSTInteger= new BinarySearchTreeOperations<>();
        BSTInteger.insert(50);
        BSTInteger.insert(40);
        BSTInteger.insert(80);
        BSTInteger.insert(70);
        BSTInteger.insert(90);
        BSTInteger.insert(75);
        BSTInteger.insert(85);
        BSTInteger.insert(100);
        BSTInteger.insert(45);
        BSTInteger.insert(30);
        BSTInteger.insert(20);

        BSTInteger.printPreorder();
        BSTInteger.printPostorder();
        BSTInteger.printInorder();
        BSTInteger.printTree();
        System.out.println(BSTInteger.find(70));
        System.out.println(BSTInteger.find(110));

        BSTInteger.useRecursion = false;

        System.out.println(BSTInteger.find(70));
        System.out.println(BSTInteger.find(110));

        //boolean deleteFlag = BSTInteger.deleteNode(45); // case 1
        //boolean deleteFlag = BSTInteger.deleteNode(70); // case 3.1
        //boolean deleteFlag = BSTInteger.deleteNode(30); // case 3.2
        //boolean deleteFlag = BSTInteger.deleteNode(80); // case 2 and case 1
        boolean deleteFlag = BSTInteger.deleteNode(40); // case 2 and case 1
        BSTInteger.printTree();

    }

}
