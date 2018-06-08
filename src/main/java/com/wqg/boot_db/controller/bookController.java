package com.wqg.boot_db.controller;

import com.wqg.boot_db.pojo.Book;
import com.wqg.boot_db.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "book")
public class bookController {

    @Autowired
    BookRepository bookRepository;


    /**
     * 获取全部信息
     * @param model
     * @return
     */
    @GetMapping("/readAll")
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
     * @param updateBook
     * @return
     */
    @PostMapping("/read")
    public String read(@ModelAttribute("updateBook")Book updateBook){
        bookRepository.save(updateBook);
        //重定向到 书列表 页面。
        return "redirect:/book/readAll";
    }

}
