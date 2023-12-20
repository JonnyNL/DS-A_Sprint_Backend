package com.keyin.DAL;
import com.keyin.Tree.BinarySearchTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/treeify")
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
    public BinarySearchTree processNumbers() {
        return bstService.constructBST();
    }

    @GetMapping List<BinarySearchTree> getPreviousTrees() {
        return bstService.getPreviousTrees();
    }




}
