package org.jnosql.diana.demo;


import com.couchbase.client.java.document.json.JsonObject;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentCollectionManager;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentConfiguration;
import org.jnosql.diana.couchbase.document.CouhbaseDocumentCollectionManagerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CouchbaseApp2 {

    public static final String DATABASE = "default";
    public static final String DOCUMENT_COLLECTION = "person";

    public static void main(String[] args)  {
        String idValue = UUID.randomUUID().toString();
        CouchbaseDocumentConfiguration configuration = new CouchbaseDocumentConfiguration();
        try(CouhbaseDocumentCollectionManagerFactory collectionFactory = configuration.get();) {
            CouchbaseDocumentCollectionManager collectionManager = collectionFactory.get(DATABASE);

            DocumentEntity entity = DocumentEntity.of(DOCUMENT_COLLECTION);
            entity.add(Document.of("name", "Daniel Soro"));
            entity.add(Document.of("age", 26));
            entity.add(Document.of("_id", idValue));
            DocumentEntity entitySaved = collectionManager.save(entity);
            Optional<Document> id = entitySaved.find("_id");

            JsonObject params = JsonObject.create();
            params.put("id", id.get().get());
            List<DocumentEntity> entities = collectionManager.n1qlQuery("select * from " + DATABASE + " where _id = $id", params);
            System.out.println(entities);


        }
    }
}
