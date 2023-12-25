package com.zb.misscmszb.controller.v1;

import com.zb.misscmszb.core.annotation.GroupRequired;
import com.zb.misscmszb.core.annotation.Logger;
import com.zb.misscmszb.core.annotation.LoginRequired;
import com.zb.misscmszb.core.annotation.PermissionMeta;
import com.zb.misscmszb.core.exception.NotFoundException;
import com.zb.misscmszb.dto.book.CreateOrUpdateBookDTO;
import com.zb.misscmszb.model.BookDO;
import com.zb.misscmszb.service.BookService;
import com.zb.misscmszb.vo.CreatedVO;
import com.zb.misscmszb.vo.DeletedVO;
import com.zb.misscmszb.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getBook/{id}")
    public BookDO getBook(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        BookDO book = bookService.getById(id);
        if (book == null) {
            throw new NotFoundException(10022);
        }
        return book;
    }

    @GetMapping("/getBooks")
    public List<BookDO> getBooks() {
        return bookService.findAll();
    }


    @GetMapping("/search")
    public List<BookDO> searchBook(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return bookService.getBookByKeyword("%" + q + "%");
    }


    @PostMapping("/create")
    public CreatedVO createBook(@RequestBody @Validated CreateOrUpdateBookDTO validator) {
        bookService.createBook(validator);
        return new CreatedVO(12);
    }


    @PostMapping("/update/{id}")
    public UpdatedVO updateBook(@PathVariable("id") @Positive(message = "{id.positive}") Integer id, @RequestBody @Validated CreateOrUpdateBookDTO validator) {
        BookDO book = bookService.getById(id);
        if (book == null) {
            throw new NotFoundException(10022);
        }
        bookService.updateBook(book, validator);
        return new UpdatedVO(13);
    }


    @GetMapping("/delete/{id}")
    @Logger(template = "删除图书")
    @GroupRequired
    @PermissionMeta(value = "删除图书", module = "图书")
    public DeletedVO deleteBook(@PathVariable("id") @Positive(message = "{id.positive}") Integer id) {
        BookDO book = bookService.getById(id);
        if (book == null) {
            throw new NotFoundException(10022);
        }
        bookService.deleteById(book.getId());
        return new DeletedVO(14);
    }
}
