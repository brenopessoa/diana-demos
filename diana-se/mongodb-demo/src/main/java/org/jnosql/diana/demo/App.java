package org.jnosql.diana.demo;


import org.jnosql.diana.api.document.*;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;

import java.util.List;
import java.util.Optional;

public class App {

    public static final String DATABASE = "database";
    public static final String DOCUMENT_COLLECTION = "person";

    public static void main(String[] args)  {
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        try(DocumentCollectionManagerFactory collectionFactory = configuration.get();) {
            DocumentCollectionManager collectionManager = collectionFactory.get(DATABASE);

            DocumentEntity entity = DocumentEntity.of(DOCUMENT_COLLECTION);
            entity.add(Document.of("name", "Daniel Soro"));
            entity.add(Document.of("age", 26));

            DocumentEntity entitySaved = collectionManager.insert(entity);
            Optional<Document> id = entitySaved.find("_id");

            DocumentQuery query = DocumentQuery.of(DOCUMENT_COLLECTION);
            query.and(DocumentCondition.eq(id.get()));
            List<DocumentEntity> documentsFound = collectionManager.select(query);


        }
    }
}
