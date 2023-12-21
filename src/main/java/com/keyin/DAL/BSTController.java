package com.keyin.DAL;
import com.keyin.Tree.BinarySearchTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/treeify")
@CrossOrigin(origins = "http://localhost:3000")
public class BSTController {

    private final BSTService bstService;
    @Autowired
    public BSTController(BSTService bstService) {
        this.bstService = bstService;
    }

    @PostMapping("/enter-numbers")
    public void enterNumbers(@RequestBody int[] numbers) {
        bstService.enterRootNums(numbers);

    }

    @GetMapping("/process-numbers")
    public ResponseEntity<BinarySearchTree> processNumbers() {
        try {
            BinarySearchTree processedTree = bstService.constructBST();
            if (processedTree != null) {
                return new ResponseEntity<>(processedTree, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/previous-trees")
    public ResponseEntity<List<BinarySearchTree>> getPreviousTrees() {
        try {
            List<BinarySearchTree> trees = bstService.getPreviousTrees();
            if (trees != null && !trees.isEmpty()) {
                return new ResponseEntity<>(trees, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
