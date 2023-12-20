package com.keyin.Tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class BinaryNode {
    // Declaring node instances as private for better control and validation attempt, may have to change...
    private int value;
    private int height;
    private BinaryNode left;
    private BinaryNode right;

    public BinaryNode() {

    }
    public BinaryNode(int value) {
        this.value = value;
        // Assuming leaf node by default
        this.height = 1;
    }

    // Adding jackson constructor method hoping this fixes inability to find serialization
    @JsonCreator
    public BinaryNode(@JsonProperty("value") int value, @JsonProperty("left") BinaryNode left, @JsonProperty("right") BinaryNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
        updateHeight();
    }

    // Getter for the value point or key of a node
    public int getValue() {
        return value;
    }
    // Setter for the value point or key of a node
    public void setValue(int value) {
        this.value = value;
    }

    // Getter for the length of the longest downward path to a leaf from node
    public int getHeight() {
        return height;
    }

    // Setter for the length of the longest downward path to a leaf from node
    public void setHeight(int height) {
        this.height = height;
    }
    private void updateHeight() {
        this.height = 1 + Math.max(height(left), height(right));
    }

    // Getter for the left side child of the node
    public BinaryNode getLeft() {
        return left;
    }

    // Setter for the left side child of the node
    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    // Getter for the right side child of a node
    public BinaryNode getRight() {
        return right;
    }

    // Setter for the right side child of a node
    public void setRight(BinaryNode right) {
        this.right = right;
    }
    // util method to calculate the height of a tree
    private int height(BinaryNode node) {
        return node == null ? 0 : node.getHeight();
    }

    // Adding jackson methods for accessing json format of binary node
    @JsonProperty("value")
    public int getJsonValue(){
        return value;
    }
    @JsonProperty("left")
    public BinaryNode getJsonLeft(){
        return left;
    }
    @JsonProperty("right")
    public BinaryNode getJsonRight(){
        return right;
    }

}