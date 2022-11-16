package lab6.product.service;

import lab6.product.entity.Product;
import lab6.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    ProductRepository productRepository;

    public List<Product> getProducts(){
        return (List<Product>) this.productRepository.findAll();
    }

    public Product getProductById(int id){
        return this.productRepository.findById(id).orElse(null);
    }

    public void deleteProductById(int id){
        this.productRepository.deleteById(id);
    }

    public Product saveProduct(Product p){
        return this.productRepository.save(p);
    }

    public List<Product> getProductsByUserId(int id){
        return this.productRepository.findProductByUserId(id);
    }
}
