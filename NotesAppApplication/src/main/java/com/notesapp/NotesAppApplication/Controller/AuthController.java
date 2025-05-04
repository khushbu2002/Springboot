package com.notesapp.NotesAppApplication.Controller;

import com.notesapp.NotesAppApplication.Model.User;
import com.notesapp.NotesAppApplication.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/api/users")
public class AuthController {

    @Autowired
    private AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        String message = userService.createUser(user);

        if (message.equals("Username already exists!")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "User updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }


//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        return userService.login(username, password);
//    }

}
