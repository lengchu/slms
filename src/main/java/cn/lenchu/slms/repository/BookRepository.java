package cn.lenchu.slms.repository;

import cn.lenchu.slms.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    public Page<Book> findByNameLike(String name, Pageable pageable);

    public Page<Book> findByAuthorLike(String author, Pageable pageable);
}
