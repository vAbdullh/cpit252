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

public class Firebase {

    private static final String SECRETS_PATH = "/etc/secrets/firebase-adminsdk-secrets.json";

    public static void init() {
        if (FirebaseApp.getApps().isEmpty()) {
            try {
                InputStream serviceAccount = Firebase.class
                        .getClassLoader()
                        .getResourceAsStream(SECRETS_PATH);

                if (serviceAccount == null) {
                    throw new RuntimeException("Firebase secret is missing");
                }
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

