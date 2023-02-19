package me.richard.wizler.controller;

import me.richard.wizler.model.Category;
import me.richard.wizler.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepo categoryRepo;






    @PostMapping("/")
    public ResponseEntity<String> add(@RequestBody Map<String,String> body){
        //Get the body

        Category category = new Category();
        category.setName(body.get("name"));

        try{
            category = categoryRepo.save(category);
            return new ResponseEntity<> ("Category added successful", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<> ("An error occurred", HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("/")
    public ResponseEntity<String> edit(@RequestBody Map<String,String> body){
        try{
            UUID id =UUID.fromString(body.get("id"));
            String name = body.get("name");
            Category category = categoryRepo.getById(id);
            category.setName(name);
            categoryRepo.save(category);
        }catch (Exception e){
            return new ResponseEntity<> ("An error occurred", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> ("Category edited successful", HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> listCategories(){
        Map<String, Object> res = new HashMap<>();

        try{
            List<Category> categories = categoryRepo.findAll();


            if(categories.isEmpty()){
                res.put("success", false);
                res.put("message","categories does not exist");
                return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
            }
            res.put("success", true);
            res.put("categories",categories);


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

             categoryRepo.deleteById(id);
        }catch (Exception e){
            return new ResponseEntity<> ("An error occurred", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<> ("Category deleted successfully", HttpStatus.ACCEPTED);
    }




}
