package DAOClasses;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.concurrent.ExecutionException;

public class Conturi {
    private final Database database = Database.getInstance();

    public boolean checkIfUserExists(String user) throws InterruptedException, ExecutionException {
        CollectionReference collection = database.db.collection("conturi");
        Query query = collection.whereEqualTo("user", user);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            if (document.getData() != null)
                return true;
        }

        return false;
    }
    public String getPassword(String user) throws InterruptedException, ExecutionException {
        CollectionReference collection = database.db.collection("conturi");
        Query query = collection.whereEqualTo("user", user);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            if (document.getData() != null)
                return document.getData().get("password").toString();
        }

        return null;
    }
    public String getRol(String user) throws InterruptedException, ExecutionException {
        CollectionReference collection = database.db.collection("conturi");
        Query query = collection.whereEqualTo("user", user);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            if (document.getData() != null)
                return document.getData().get("rol").toString();
        }

        return null;
    }
}
