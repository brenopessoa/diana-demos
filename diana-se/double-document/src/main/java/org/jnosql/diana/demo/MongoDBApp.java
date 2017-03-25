package org.jnosql.diana.demo;


import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentCondition;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.api.document.DocumentQuery;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MongoDBApp {

    public static final String DATABASE = "default";
    public static final String DOCUMENT_COLLECTION = "person";

    public static void main(String[] args)  {
        String idValue = UUID.randomUUID().toString();
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        try(DocumentCollectionManagerFactory collectionFactory = configuration.get();) {
            DocumentCollectionManager collectionManager = collectionFactory.get(DATABASE);

            DocumentEntity entity = DocumentEntity.of(DOCUMENT_COLLECTION);
            entity.add(Document.of("name", "Daniel Soro"));
            entity.add(Document.of("age", 26));
            entity.add(Document.of("_id", idValue));

            DocumentEntity entitySaved = collectionManager.save(entity);
            Optional<Document> id = entitySaved.find("_id");

            DocumentQuery query = DocumentQuery.of(DOCUMENT_COLLECTION);
            query.and(DocumentCondition.eq(id.get()));
            List<DocumentEntity> documentsFound = collectionManager.find(query);


        }
    }
}
