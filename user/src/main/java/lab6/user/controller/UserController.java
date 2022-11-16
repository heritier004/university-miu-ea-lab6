package lab6.user.controller;

import lab6.user.entity.Users;
import lab6.user.service.UserService;
import lab6.user.types.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<Users>> getUsers(){
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id){
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    public List<Product> getProductsByUserId(@PathVariable int id){
        Product p = restTemplate.getForObject("http://products-service/products/filter/userid/"+id,
                Product.class);

        System.out.println("AAAAAA: "+ p);

        return null;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody Users user){
        Users u = this.userService.addUser(user);
        if(u != null){
            return new ResponseEntity<>("successsfully added user", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("unable to add user", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody Users user){
        return this.addUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id){
        this.userService.deleteUserById(id);
        return new ResponseEntity<>("successsfully added user", HttpStatus.OK);
    }
}
