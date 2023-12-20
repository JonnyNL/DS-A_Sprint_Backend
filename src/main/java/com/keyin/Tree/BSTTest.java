package com.keyin.Tree;

public class BSTTest {

    public static void main(String[] args) {
        // Creating new binary search tree for testing
        System.out.println(" ");
        System.out.println("Created new Binary Tree Instance");
        System.out.println(" ");
        BinarySearchTree testTree =  new BinarySearchTree();

        // Inserting random values to root of tree
        System.out.println("Inserting the values 6,1,9,3,5 to root of tree using insert() method");
        testTree.insert(6);
        testTree.insert(1);
        testTree.insert(9);
        testTree.insert(3);
        testTree.insert(5);

        // Displaying these values using level order traversal
        System.out.println(" ");
        System.out.println("vv Displaying values using level order traversal vv");
        testTree.levelOrder();

        System.out.println(" ");

        // Serializing Tree using method defined in BinarySearchTree to view as json
        System.out.println("vv Serializing tree to JSON using serialize() method vv");
        String serializedTree = testTree.serialize();
        System.out.println(" ");

        // Displaying json format of tree
        System.out.println("vv Serialized Tree vv");
        System.out.println(serializedTree);

        // Deserializing tree
        BinarySearchTree deserializedTree = BinarySearchTree.deserialize(serializedTree);
        System.out.println(" ");
        System.out.println("vv Deserialized Tree vv");
        deserializedTree.levelOrder();



    }

}
