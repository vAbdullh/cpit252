package sa.edu.kau.fcit.vecabtrainer.controller;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.edu.kau.fcit.vecabtrainer.config.FirestoreInit;
import sa.edu.kau.fcit.vecabtrainer.model.Category;

@RestController
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        logger.info("Request received: GET /categories");
        Firestore db = FirestoreInit.getFirestore();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("categories").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Category> categories = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) {
                Category category = document.toObject(Category.class);
                if (category != null) {
                    categories.add(category);
                }
            }
            logger.info("Found {} categories", categories.size());
            return categories;
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error fetching categories", e);
            Thread.currentThread().interrupt();
            return new ArrayList<>();
        }
    }
}
