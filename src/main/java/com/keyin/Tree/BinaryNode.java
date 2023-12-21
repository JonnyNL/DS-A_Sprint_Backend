package com.keyin.Tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
public class BinaryNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "left_node_id")
    private BinaryNode left;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "right_node_id")
    private BinaryNode right;

    public BinaryNode() {
    }

    public BinaryNode(int value) {
        this.value = value;
    }

    @JsonCreator
    public BinaryNode(@JsonProperty("value") int value, @JsonProperty("left") BinaryNode left, @JsonProperty("right") BinaryNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    // Getter for the value point or key of a node
    public int getValue() {
        return value;
    }
    // Setter for the value point or key of a node
    public void setValue(int value) {
        this.value = value;
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