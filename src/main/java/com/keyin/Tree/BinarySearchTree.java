package com.keyin.Tree;
import com.keyin.Tree.BinaryNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;


public class BinarySearchTree {
    private BinaryNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int value) {
        root = insert(root, value);
    }
    // Structuring node placement on bst
    private BinaryNode insert(BinaryNode currentNode, int value) {
        if (currentNode == null) {
            return new BinaryNode(value);
        } else if (value < currentNode.getValue()) {
            currentNode.setLeft(insert(currentNode.getLeft(), value));
        } else {
            currentNode.setRight(insert(currentNode.getRight(), value));
        }
        return currentNode;
    }
    // Using jackson to serialize tree to json
    public String serialize() {
        try {
            // Creating a new objectmapper instance to define settings such as letting null appear
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(root);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}