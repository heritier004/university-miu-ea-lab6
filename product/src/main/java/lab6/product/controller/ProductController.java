package lab6.product.controller;

import lab6.product.entity.Product;
import lab6.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @GetMapping
    public List<Product> getPrdoucts(){
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getPrdouctById(@PathVariable int id){
        return this.productService.getProductById(id);
    }

    @GetMapping("/filter/userid/{id}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable int id){
        List<Product> p = this.productService.getProductsByUserId(id);
        if(p != null)
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(p, HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product prod){
        Product p = this.productService.saveProduct(prod);
        if(p != null){
            return new ResponseEntity<>("successfully added product", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("unable to add product", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(Product p){
        return this.addProduct(p);
    }
}
