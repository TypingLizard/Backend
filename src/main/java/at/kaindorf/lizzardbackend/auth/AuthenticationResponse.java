package at.kaindorf.lizzardbackend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project: Typing_Lizzard_Backend
 * Author : Alexander Friedl
 * Date : 12.06.2024
 * Time : 17:49
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
}
