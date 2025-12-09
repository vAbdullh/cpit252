package sa.edu.kau.fcit.vecabtrainer.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sa.edu.kau.fcit.vecabtrainer.config.Firebase;
import sa.edu.kau.fcit.vecabtrainer.model.Deck;

@RestController
public class DeckController {

    private static final Logger logger = LoggerFactory.getLogger(DeckController.class);

    @GetMapping("/decks/{category}")
    public List<Deck> getDeckByCategory(@PathVariable String category) {
        logger.info("Request received: GET /decks/{}", category);
        Firestore db = Firebase.getFirestore();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("decks").whereEqualTo("category", category).get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Deck> decks = new ArrayList<>();
            for (DocumentSnapshot document : documents) {
                decks.add(document.toObject(Deck.class));
            }
            logger.info("Found {} decks for category: {}", decks.size(), category);
            return decks;
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error fetching decks for category: {}", category, e);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
