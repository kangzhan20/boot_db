package com.wqg.boot_db.controller;

import com.wqg.boot_db.pojo.Book;
import com.wqg.boot_db.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class bookController {

    @Autowired
    BookRepository bookRepository;


    /**
     * 获取全部信息
     * @param model
     * @return
     */
    @RequestMapping(value="/readAll",method = RequestMethod.GET)
    public String readAll(Model model){
        List<Book> list=bookRepository.findAll();
        model.addAttribute("bookList",list);
        return "bookList";
    }

    /**
     * 读取单一信息
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/read")
    public String read(Model model,@RequestParam("id")Long id){
        model.addAttribute("book",bookRepository.findById(id));
        return "book";
    }

    /**
     * 创建或者更新信息
     * @param id
     * @param author
     * @param isbn
     * @param title
     * @param description
     * @return
     */
    @PostMapping("/read")
    public String read(@RequestParam("id")Long id,@RequestParam("author")String author,
                       @RequestParam("isbn")String isbn,@RequestParam("title")String title,
                       @RequestParam("description")String description){
        Object obj=bookRepository.findById(id);
        if(obj==null){
            Book book=new Book();
            book.setAuthor(author);
            book.setDescription(description);
            book.setIsbn(isbn);
            book.setTitle(title);
            bookRepository.save(book);
        }else{
            bookRepository.updateBook(id,author,isbn,title,description);
        }

        //重定向到 书列表 页面。
        return "redirect:/readAll";
    }

}
