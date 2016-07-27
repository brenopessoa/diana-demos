package org.apache.diana.demo;


import org.apache.diana.api.document.*;
import org.apache.diana.mongodb.document.MongoDBDocumentConfiguration;

import java.util.List;
import java.util.Optional;

public class App {

    public static final String DATABASE = "database";
    public static final String DOCUMENT_COLLECTION = "person";

    public static void main(String[] args)  {
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        try(DocumentCollectionManagerFactory collectionFactory = configuration.getManagerFactory();) {
            DocumentCollectionManager collectionManager = collectionFactory.getDocumentEntityManager(DATABASE);

            DocumentCollectionEntity entity = DocumentCollectionEntity.of(DOCUMENT_COLLECTION);
            entity.add(Document.of("name", "Daniel Soro"));
            entity.add(Document.of("age", 26));

            DocumentCollectionEntity entitySaved = collectionManager.save(entity);
            Optional<Document> id = entitySaved.find("_id");

            DocumentQuery query = DocumentQuery.of(DOCUMENT_COLLECTION);
            query.addCondition(DocumentCondition.eq(id.get()));
            List<DocumentCollectionEntity> documentsFound = collectionManager.find(query);


        }
    }
}
