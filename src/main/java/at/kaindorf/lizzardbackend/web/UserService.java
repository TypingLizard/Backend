package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.UserRepository;
import at.kaindorf.lizzardbackend.pojos.Mode;
import at.kaindorf.lizzardbackend.pojos.User;
import at.kaindorf.lizzardbackend.pojos.Word;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 10.04.2024
 * Time : 11:03
 */
/*
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;


    @PostMapping("/signup")
    public ResponseEntity<String> signUpAsMember(@RequestBody User newUser) {
        Long emailInUse = userRepo.isEmailInUse(newUser.getEmail());
        if (emailInUse == null){
            userRepo.save(newUser);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .build()
                    .toUri();

            return ResponseEntity.created(location).body("Sign Up Successful!");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> optionalUser = userRepo.findById(id);

        return ResponseEntity.of(optionalUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return ResponseEntity.accepted().body(id);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Boolean> changeUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {

            userRepo.save(updatedUser);

            return ResponseEntity.accepted().body(true);
        }
        return ResponseEntity.notFound().build();
    }

    // get the user to login
    // post a new user
    // update an user



}
*/