package at.kaindorf.lizzardbackend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: Typing_Lizard_Backend
 * Author : Alexander Friedl
 * Date : 12.06.2024
 * Time : 17:50
 */

// the Body that the user needs to make a /register post
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
}
