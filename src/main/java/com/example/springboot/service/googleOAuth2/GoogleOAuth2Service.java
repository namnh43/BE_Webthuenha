package com.example.springboot.service.googleOAuth2;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import java.util.Collections;

public class GoogleOAuth2Service {
    private static final String CLIENT_ID = "696354615975-8ivuevdnm3gh9anklroh9kq3mfh4nkog.apps.googleusercontent.com";

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

                // Get profile information from the payload
                String email = payload.getEmail();
                boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                System.out.println("Email: " + email + "\nEmail verified: " + emailVerified + "\nName: " + name + "\nPicture URL: " + pictureUrl + "\nLocale: " + locale + "\nFamily name: " + familyName + "\nGiven name: " + givenName);

            } else {
                // Token is invalid
                throw new RuntimeException("Invalid Google ID token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xác thực token: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String accessToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzYmRiZmRlZGUzYmFiYjI2NTFhZmNhMjY3OGRkZThjMGIzNWRmNzYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2ODk5ODkxMzYsImF1ZCI6IjY5NjM1NDYxNTk3NS04aXZ1ZXZkbm0zZ2g5YW5rbHJvaDlrcTNtZmg0bmtvZy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwMjYwODgyNzUyNTY3OTIyMTk1MCIsImVtYWlsIjoibWluaHR1YW4yNjdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjY5NjM1NDYxNTk3NS04aXZ1ZXZkbm0zZ2g5YW5rbHJvaDlrcTNtZmg0bmtvZy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJNIFQiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFjSFR0Zkxtbk4tLUFJN0E5aHNickR3NDZCLWZ6TWNRdlN2SGlNT1RvUHprd3R6cTJFPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6Ik0iLCJmYW1pbHlfbmFtZSI6IlQiLCJpYXQiOjE2ODk5ODk0MzYsImV4cCI6MTY4OTk5MzAzNiwianRpIjoiYzFhMGE2OTcwNzRhYjYwODcxMDE3MmE1YmNkMmY0ZjdkMzlmMTIwNyJ9.Uu9QzADZuacZPEr-ZgrsXKM9hCaCEVyWgt5PCutBDfUtcgGIKZ6wMmEthGM34uW5NF4W8VIK9i9-4kF8XyQ9pZqg0vl3E1hyGw6QWUD44naowX6A8N55wlx0i5HoCJSh9lCrHX-YELnFE3TVUFNfkCWHmZocBg4xCGVSp8UlCiENGoH11yNVDyL7ZqqbBCL7KISyda66LdTwzzFnHDyfqfCX5wK9rwJ_XRSGks-y70Bw-ZA3qZoFZ85w68xGEzEmOy4InIb_a9xKJ8OLly2rl6cNccKpzprqU8fNz8zDSDYcKmLJ72xMpxq_O42RyRL4EJJ9Ps_vH1OaW9wBgKTX_w";
        test(accessToken);
    }
}

