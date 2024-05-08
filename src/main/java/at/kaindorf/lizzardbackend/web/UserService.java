package at.kaindorf.lizzardbackend.web;

import at.kaindorf.lizzardbackend.database.UserRepository;
import at.kaindorf.lizzardbackend.pojos.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 10.04.2024
 * Time : 11:03
 */

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
    // get the user to login
    // post a new user
    // update an user



}
