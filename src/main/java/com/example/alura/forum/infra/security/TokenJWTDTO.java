package com.example.alura.forum.infra.security;

public record TokenJWTDTO(String token) {
    /* turn the response token from just the token to a json formatted token with the name "token"
        {
          "token": "your token"
        }
    */
}
