package com.example.springboot.service.googleOAuth2;

import com.example.springboot.dto.response.JwtAuthenticationResponse;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.JwtService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
                newUser.setRole(Role.ROLE_USER);
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
        test("eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzYmRiZmRlZGUzYmFiYjI2NTFhZmNhMjY3OGRkZThjMGIzNWRmNzYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2OTAxNjg4MzEsImF1ZCI6IjY5NjM1NDYxNTk3NS04aXZ1ZXZkbm0zZ2g5YW5rbHJvaDlrcTNtZmg0bmtvZy5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNDIzNjEwNjczNzIxMjk3Mzk0NyIsImVtYWlsIjoibWluaHR1YW4yNjc5N0BnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXpwIjoiNjk2MzU0NjE1OTc1LThpdnVldmRubTNnaDlhbmtscm9oOWtxM21maDRua29nLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwibmFtZSI6IlBo4bqhbSBNaW5oIFR14bqlbiIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQWNIVHRkZ0M5Q1l2Y0xMUW5BNVJ1MV9RNGFrZVZKdUY2eWxQS1M0alQzSU5CaDc9czk2LWMiLCJnaXZlbl9uYW1lIjoiUGjhuqFtIE1pbmgiLCJmYW1pbHlfbmFtZSI6IlR14bqlbiIsImlhdCI6MTY5MDE2OTEzMSwiZXhwIjoxNjkwMTcyNzMxLCJqdGkiOiJkYWI0MTgxYmFhMTRlZTA5MDAzZThlYmYxMmU5N2QzMzMyYTVjMTRjIn0.RR8Avnz599ntsBLxtiZS13qBc23ELs7E_ed0_BMK6obzmS3bNe4TOmeJxbQaouDWOIpkRIIIpRr8ptD5IuoOr9p27Cdtgu2s4vRtE2k3pMj5y9kBB-yGI25yOdAny8rWR_9Is2k_dbzBLasiS5rmxycwq4ECn15Z-MnNBUIbLKcuXfd_4HLiF2yL_ZfXmJH4WdjG49HJ8IYR_gDpJJss6XRjeUg4KkE4Xm_3hgSyVzJL9J2YGY4hLVj2pYnyQo52mCa6Y_ny9jb72YS3Pd0mqst3AhkKPrdCCXq6dUqmJAed65uPzt5vpIGEtQoAtlGSCIfVT9r2ppgPjqLfGwgt7g");
    }
}

