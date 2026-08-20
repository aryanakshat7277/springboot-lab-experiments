package com.example.MultiDatabases.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.MultiDatabases.db1.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
