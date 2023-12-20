package com.keyin.DAL;
import java.util.ArrayList;
import java.util.List;
import com.keyin.Tree.BinarySearchTree;
import org.springframework.stereotype.Service;

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
            previousTrees.add(currentTree);
        }
        return currentTree;
    }

    public List<BinarySearchTree> getPreviousTrees() {
        return previousTrees;
    }
}
