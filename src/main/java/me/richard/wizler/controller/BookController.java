package me.richard.wizler.controller;

import me.richard.wizler.model.Book;
import me.richard.wizler.model.Category;
import me.richard.wizler.repo.BookRepo;
import me.richard.wizler.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    BookRepo bookRepo;






    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody Map<String,String> body){
        //Get the body
        Book book = new Book();
        book.setAuthor(body.get("author"));
        book.setTitle(body.get("title"));

        try{
            book = bookRepo.save(book);

            //Check if the category is present
            if(!body.get("category_id").isEmpty()){
                Category category = categoryRepo.findById( UUID.fromString(body.get("category_id"))).get();
                book.setCategory(category);
                bookRepo.save(book);

            }
            return new ResponseEntity<> ("Book added successful", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<> ("An error occurred", HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("/")
    public ResponseEntity<String> edit(@RequestBody Map<String,String> body){
        try{
            UUID id =UUID.fromString(body.get("id"));
            String title = body.get("title");
            String author = body.get("author");
            Book book = bookRepo.findById(id).get();
            book.setAuthor(author);
            book.setTitle(title);

            bookRepo.save(book);
        }catch (Exception e){
            return new ResponseEntity<> ("An error occurred", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> ("Category edited successful", HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> listBooks(){
        Map<String, Object> res = new HashMap<>();
        System.out.println("Gotten herennnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");

        try{
            List<Book>books = bookRepo.findAll();


            if(books.isEmpty()){
                res.put("success", false);
                res.put("message","books are empty");
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
            }
            res.put("success", true);
            res.put("books",books);


        }catch (Exception e){
            res.put("success", false);
            res.put("message","An error occured");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (res, HttpStatus.ACCEPTED);
    }

    @PutMapping("/books")
    public ResponseEntity<Map<String, Object>> addBooksToCategory(@RequestBody Map<String,String> body){
        Map<String, Object> res = new HashMap<>();
        UUID book_id = UUID.fromString(body.get("book_id"));
        UUID cat_id = UUID.fromString(body.get("category_id"));


        try{
            Category category = categoryRepo.findById(cat_id).get();
            Book book = bookRepo.findById(book_id).get();

            book.setCategory(category);
            bookRepo.save(book);




            res.put("success", true);
            res.put("book",book);


        }catch (Exception e){
            res.put("success", false);
            res.put("message","An error occured");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> (res, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestBody Map<String,String> body){
        try{
            UUID id =UUID.fromString(body.get("id"));

            bookRepo.deleteById(id);
        }catch (Exception e){
            return new ResponseEntity<> ("An error occurred", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> ("Book deleted successfully", HttpStatus.ACCEPTED);
    }



}
