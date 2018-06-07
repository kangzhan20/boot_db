package com.wqg.boot_db.repository;

import com.wqg.boot_db.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Long> {

    /**
     * 使用 @Query注解 进行sql语句update Book信息。
     * @param id
     * @param author
     * @param isbn
     * @param title
     * @param description
     */
    @Query(value ="update Book set author=?2 ,isbn=?3,title=?4,description=?5 where id=?1", nativeQuery = true)
    @Modifying
    void updateBook(long id,String author,String isbn,String title,String description);
}
