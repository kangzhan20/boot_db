package com.wqg.boot_db.repository;

import com.wqg.boot_db.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book,Long> {

    /**
     * 使用 @Query注解 进行sql语句update Book信息。
     * @param id
     * @param author
     * @param isbn
     * @param title
     * @param description
     */
    @Query(value ="update Book set author=:author ,isbn=:isbn," +
            "title=:title,description=:description where id=:id", nativeQuery = true)
    @Modifying
    void updateBook(@Param("id") long id,@Param("author") String author,
                    @Param("isbn")String isbn,@Param("title") String title,@Param("description") String description);
}
