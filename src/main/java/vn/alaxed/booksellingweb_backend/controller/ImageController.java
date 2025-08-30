package vn.alaxed.booksellingweb_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.alaxed.booksellingweb_backend.entity.Image;
import vn.alaxed.booksellingweb_backend.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable int id){
        System.out.println("id" + id);
        try{
            imageService.deleteImage(id);
            return ResponseEntity.ok().body(true);
        }catch(RuntimeException e){
            System.out.println("---------"+e);
            return ResponseEntity.ok().body(false);
        }
    }

    @PostMapping("/add/{bookId}")
    public ResponseEntity<Image> postMethodName(@PathVariable int bookId, @RequestBody Image image) {
       Image img = imageService.addImage(bookId, image);
        
        return new ResponseEntity<>(img,HttpStatus.CREATED);
    }
    
}
