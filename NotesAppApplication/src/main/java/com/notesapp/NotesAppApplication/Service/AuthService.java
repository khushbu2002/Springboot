package com.notesapp.NotesAppApplication.Service;

import com.notesapp.NotesAppApplication.Model.User;
import com.notesapp.NotesAppApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {
        if (userRepository.userExists(user.getUsername())) {
            return "Username already exists!";
        }

        int result = userRepository.save(user);
        return result > 0 ? "User registered successfully" : "Registration failed!";
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int updateUser(User user) {
        return userRepository.update(user);
    }

    public int deleteUser(int id) {

        return userRepository.delete(id);
    }



//    public String login(String username, String password) {
//        User user = userRepository.loginUser(username, password);
//        return (user != null) ? "Login successful!" : "Invalid credentials!";
//    }


}
