package com.keyin.DAL;
import java.util.ArrayList;
import java.util.List;
import com.keyin.Tree.BinarySearchTree;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;

// SERVICE LAYER FOR BINARY TREES
@Service
public class BSTService {
    //s
    private final List<BinarySearchTree> previousTrees = new ArrayList<>();
    private BinarySearchTree currentTree;

    public void enterRootNums(int[] numbers) {
        currentTree = new BinarySearchTree();
        for (int number : numbers) {
            currentTree.insert(number);
        }
    }

    public BinarySearchTree constructBST() {
        if (currentTree != null) {
            saveCurrentTree();
            previousTrees.add(currentTree);
        }
        return currentTree;
    }

    public List<BinarySearchTree> getPreviousTrees() {
        return previousTrees;
    }

    @Autowired
    private EntityManager entityManager;
    @Transactional
    public void saveCurrentTree() {
        if (currentTree != null) {
            currentTree.saveToDatabase(entityManager);
        }
    }
}
