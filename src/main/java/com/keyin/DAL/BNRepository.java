package com.keyin.DAL;

import com.keyin.Tree.BinaryNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BNRepository extends JpaRepository<BinaryNode, Long> {
}