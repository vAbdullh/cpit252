package sa.edu.kau.fcit.vecabtrainer.proxies;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import sa.edu.kau.fcit.vecabtrainer.config.Firebase;

public class AuthCheck {

    public static FirebaseToken verifyToken(String idToken) {
        try {
            return Firebase.getFirebaseAuth().verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Auth verification failed", e);
        }
    }
}
