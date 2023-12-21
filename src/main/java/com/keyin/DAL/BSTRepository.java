package com.keyin.DAL;

import com.keyin.Tree.BinarySearchTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BSTRepository extends JpaRepository<BinarySearchTree, Long> {
}