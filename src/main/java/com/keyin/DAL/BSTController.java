package com.keyin.DAL;

import com.keyin.Tree.BinarySearchTree;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/treeify")
@CrossOrigin(origins = "http://localhost:3000")
public class BSTController {

    private final List<BinarySearchTree> trees = new ArrayList<>();
    // Routing process-numbers to postmapping to take users array of integers
    @PostMapping("/process-numbers")
    public String processInput(@RequestParam("numbers") String nmbersEntered) {
        BinarySearchTree bsTree = new BinarySearchTree();

        String[] numbersList = nmbersEntered.split("\\s+");
        for (String number : numbersList) {
            int num = Integer.parseInt(number.trim());
            bsTree.insert(num);
        }

        String jsonFormatTree = bsTree.serialize();
        trees.add(bsTree);
        return jsonFormatTree;
    }
    // Routing /previous-trees to get mapping to get all possible trees, store them to an array and then iterate through them converting to json
    @GetMapping("/previous-trees")
    public List<String> previousTrees() {
        List<String> jsonTrees = new ArrayList<>();
        for (BinarySearchTree tree : trees) {
            jsonTrees.add(tree.serialize());
        }
        return jsonTrees;
    }
}