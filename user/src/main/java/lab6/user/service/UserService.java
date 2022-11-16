package lab6.user.service;

import lab6.user.entity.Users;
import lab6.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public List<Users> getUsers(){
        return (List<Users>) this.userRepository.findAll();
    }

    public Users getUserById(int id){
        return this.userRepository.findById(id).orElse(null);
    }

    public Users addUser(Users user){
        return this.userRepository.save(user);
    }

    public void deleteUserById(int id){
        this.userRepository.deleteById(id);
    }
}
