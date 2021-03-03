package am.bigshopdemo.demo.service;

import am.bigshopdemo.demo.model.User;
import am.bigshopdemo.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User getOne(int id) {
        return userRepository.getOne(id);
    }
}
