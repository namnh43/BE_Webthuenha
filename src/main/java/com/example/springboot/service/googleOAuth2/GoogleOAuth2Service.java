package com.example.springboot.service.googleOAuth2;

import com.example.springboot.dto.response.JwtAuthenticationResponse;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.JwtService;
import com.example.springboot.service.user.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class GoogleOAuth2Service {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    private static final String CLIENT_ID = "696354615975-8ivuevdnm3gh9anklroh9kq3mfh4nkog.apps.googleusercontent.com";
    private static int count = 0;

    public static void test(String accessToken) {
        try {
            // Verify the Google ID token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new GsonFactory()
            )
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(accessToken);
            System.out.println(verifier);
            if (idToken != null) {
                // Token is valid
                Payload payload = idToken.getPayload();

                // Get user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                System.out.println("Email: " + email + "\nEmail verified: " + emailVerified + "\nName: " + name + "\nPicture URL: " + pictureUrl + "\nLocale: " + locale + "\nFamily name: " + familyName + "\nGiven name: " + givenName + "\nPhone number: ");

            } else {
                // Token is invalid
                throw new RuntimeException("Invalid Google ID token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xác thực token: " + e.getMessage());
        }
    }

    public JwtAuthenticationResponse ggOAuth2Login(String accessToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), new GsonFactory()
            )
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(accessToken);
            if (idToken != null) {
                Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                Optional<User> userOptional = userRepository.findByUsername(email);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    var response = jwtService.responseJWT(user);
                    return response;
                }

                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");
                String phoneNumber = "NoInformation" + ++count;

                User newUser = new User();
                newUser.setUsername(email);
                newUser.setEmail(email);
                newUser.setPhoneNumber(phoneNumber);
                newUser.setProfileImage(pictureUrl);
                newUser.setFirstName(givenName);
                newUser.setLastName(familyName);
                newUser.setAddress(locale);
                newUser.setRole(Role.USER);
                userRepository.save(newUser);

                var response = jwtService.responseJWT(newUser);
                return response;

            } else {
                // Token is invalid
                throw new RuntimeException("Invalid Google ID token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xác thực token: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        test("eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzYmRiZmRlZGUzYmFiYjI2NTFhZmNhMjY3OGRkZThjMGIzNWRmNzYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2OTAwMzkzODYsImF1ZCI6IjY5NjM1NDYxNTk3NS04aXZ1ZXZkbm0zZ2g5YW5rbHJvaDlrcTNtZmg0bmtvZy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwMjYwODgyNzUyNTY3OTIyMTk1MCIsImVtYWlsIjoibWluaHR1YW4yNjdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjY5NjM1NDYxNTk3NS04aXZ1ZXZkbm0zZ2g5YW5rbHJvaDlrcTNtZmg0bmtvZy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJNIFQiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFjSFR0Zkxtbk4tLUFJN0E5aHNickR3NDZCLWZ6TWNRdlN2SGlNT1RvUHprd3R6cTJFPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6Ik0iLCJmYW1pbHlfbmFtZSI6IlQiLCJpYXQiOjE2OTAwMzk2ODYsImV4cCI6MTY5MDA0MzI4NiwianRpIjoiNGYwMjIwZThlZmFlZWExNGQ3NmI0ZDNmYzM3MDM2NjY4NjM3YjIwOCJ9.UpSHMi08J_rDiIvv0m38hmu1iOr4PU2_YuOCuCxdCzJ_PaVDKJmp4ej3AdKMJiCkHPXHAXb8yUGClMoUnn_SbOZFJI0LjtTlbJ0psbjzWu8FYuJ7ZbEHG0h6ou-PNW98520nyYZNptYaR1DV8bH4MHnWffF0zlvI0hmtv_kH_RJSmmASXc7esAhaTcuAcOFdV9_EqjHUKwSYcgcvqBRREhohHr4RVQmDhTo_APqGF1v7tgvO_Ay6oArMsETNogLpcBMMWSc24ZMCKcVj6LVgFq8aMl7dBHYTteV5Svd-K0PDv6Nk-faL5T1huKZcRPUclv3MXKMqvpmz2VgLrFw2mg");
    }
}

