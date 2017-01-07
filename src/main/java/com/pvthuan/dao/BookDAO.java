package com.pvthuan.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pvthuan.model.Book;

public interface BookDAO extends JpaRepository<Book,Long>{

}
