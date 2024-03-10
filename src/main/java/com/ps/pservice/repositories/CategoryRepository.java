package com.ps.pservice.repositories;

import com.ps.pservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}