package com.keyin.Tree;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Exception;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
@Entity
public class BinarySearchTree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_node_id", referencedColumnName = "id")
    private BinaryNode root;
    public BinarySearchTree() {
        this.root = null;
    }

    // Insert Method
    private BinaryNode insert(BinaryNode currentNode, int value) {
        if (currentNode == null) {
            return new BinaryNode(value);
        } else if (value <= currentNode.getValue()) {
            currentNode.setLeft(insert(currentNode.getLeft(), value));
        } else {
            currentNode.setRight(insert(currentNode.getRight(), value));
        }
        return currentNode;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    // PreOrder Traversal
    public void preOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getValue() + " ");
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    // Inorder Traversal
    public void inOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.print(node.getValue() + " ");
        inOrder(node.getRight());
    }

    // PostOrder Traversal
    public void postOrder(BinaryNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getValue() + " ");
    }

    // Level Order
    public void levelOrder() {
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode presentNode = queue.remove();
            System.out.print(presentNode.getValue() + " ");
            if (presentNode.getLeft() != null) {
                queue.add(presentNode.getLeft());
            }
            if (presentNode.getRight() != null) {
                queue.add(presentNode.getRight());
            }
        }
    }

    // Search Method
    public BinaryNode search(BinaryNode node, int value) {
        if (node == null) {
            System.out.println("Value: " + value + " not found in BST");
            return null;
        } else if (node.getValue() == value) {
            System.out.println("Value: " + value + " found in BST");
            return node;
        } else if (value < node.getValue()) {
            return search(node.getLeft(), value);
        } else {
            return search(node.getRight(), value);
        }
    }

    // Minimum node
    public static BinaryNode minimumNode(BinaryNode root) {
        if (root.getLeft() == null) {
            return root;
        } else {
            return minimumNode(root.getLeft());
        }
    }

    // Maximum node
    public static BinaryNode maximumNode(BinaryNode root) {
        if (root.getRight() == null) {
            return root;
        } else {
            return maximumNode(root.getRight());
        }
    }

    // Delete node
    public BinaryNode deleteNode(BinaryNode root, int value) {
        if (root == null) {
            System.out.println("Value not found in BST");
            return null;
        }
        if (value < root.getValue()) {
            root.setLeft(deleteNode(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(deleteNode(root.getRight(), value));
        } else {
            if (root.getLeft() != null && root.getRight() != null) {
                BinaryNode minNodeForRight = minimumNode(root.getRight());
                root.setValue(minNodeForRight.getValue());
                root.setRight(deleteNode(root.getRight(), minNodeForRight.getValue()));
            } else if (root.getLeft() != null) {
                root = root.getLeft();
            } else if (root.getRight() != null) {
                root = root.getRight();
            } else {
                root = null;
            }
        }
        return root;
    }

    public void deleteBST() {
        root = null;
        System.out.println("BST has been deleted successfully");
    }

    // Adding Jackson methods to serialize and deserialize tweeeees

    public String serialize() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(root);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static BinarySearchTree deserialize(String json) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            BinaryNode rootNode = objectMapper.readValue(json, BinaryNode.class);

            BinarySearchTree tree = new BinarySearchTree();
            tree.root = rootNode;
            return tree;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Need a method to save json to database
    public void saveToDatabase(EntityManager entityManager) {
        try {
            String jsonTree = serialize();
            if (jsonTree != null) {
                entityManager.persist(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}