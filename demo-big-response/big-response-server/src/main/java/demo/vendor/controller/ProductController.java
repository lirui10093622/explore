package demo.vendor.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @RequestMapping("all")
    public ResponseEntity<InputStreamResource> getAllProducts(HttpServletResponse response) throws FileNotFoundException {
        InputStreamResource fis = new InputStreamResource(this.getClass().getClassLoader().getResourceAsStream("product.json"));
        return new ResponseEntity<>(fis, HttpStatus.OK);
    }
}