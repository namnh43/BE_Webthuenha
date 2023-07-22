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

                System.out.println(email + " " + emailVerified + " " + name + " " + pictureUrl + " " + locale + " " + familyName + " " + givenName);
            } else {
                // Token is invalid
                throw new RuntimeException("Invalid Google ID token.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xác thực token: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        test("eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzYmRiZmRlZGUzYmFiYjI2NTFhZmNhMjY3OGRkZThjMGIzNWRmNzYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2ODk5NTMzOTMsImF1ZCI6IjY5NjM1NDYxNTk3NS04aXZ1ZXZkbm0zZ2g5YW5rbHJvaDlrcTNtZmg0bmtvZy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwMjYwODgyNzUyNTY3OTIyMTk1MCIsImVtYWlsIjoibWluaHR1YW4yNjdAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjY5NjM1NDYxNTk3NS04aXZ1ZXZkbm0zZ2g5YW5rbHJvaDlrcTNtZmg0bmtvZy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJNIFQiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFjSFR0Zkxtbk4tLUFJN0E5aHNickR3NDZCLWZ6TWNRdlN2SGlNT1RvUHprd3R6cTJFPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6Ik0iLCJmYW1pbHlfbmFtZSI6IlQiLCJpYXQiOjE2ODk5NTM2OTMsImV4cCI6MTY4OTk1NzI5MywianRpIjoiNWVhOTBkYjgzNjdiM2RjNjJkZWEwYTczODM1M2MyNGFiYzlkNTE4NSJ9.UdrylHPfdQW018Oo5kJXL8904PesP2CO1ugsUdUMynLfzEo-wL2TdxVu5WBFeC65uIdQGpdjHhy-RSD6ifFgmlkuy6rqZveTfaSwNYvfcNunnGe7VQJ4xViVO86jL5URQEkJPO9VDK3EJQRMUn3j88sh-_3XPgW3zQ9weL20Y8of0YZkXyc5sUT_8A24cCECMnIthjH2geUbG9Nk9FH_PhJwsCahBcEeCYeCJ5Mu9yU_nBAZp12rnRYDxazulz-BSjaL1fd8kX6YHpRZ9cD4Zu3eAIsVPy_n3Xw4OE6Ow9qPSwcSvydh1fQj5ezEUpTTzXiyEUDNuppZ4sBd7KK0ng");
    }
}

