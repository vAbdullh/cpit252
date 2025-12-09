package sa.edu.kau.fcit.vecabtrainer.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class Firebase {

    public static void init() {
        if (FirebaseApp.getApps().isEmpty()) {
            try {
                String credentials = System.getenv("FIREBASE_CREDENTIALS");
                if (credentials == null || credentials.isEmpty()) {
                     throw new RuntimeException("Environment variable FIREBASE_CREDENTIALS is missing");
                }

                InputStream serviceAccount = new ByteArrayInputStream(credentials.getBytes(StandardCharsets.UTF_8));
                
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase is ready.");
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize Firebase", e);
            }
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    public static FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}

