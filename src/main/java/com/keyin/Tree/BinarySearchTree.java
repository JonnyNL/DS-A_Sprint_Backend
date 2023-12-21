package com.keyin.Tree;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.keyin.Tree.BinaryNode;
public class BinarySearchTree {
    private BinaryNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

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

    public String serialize() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(root);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}