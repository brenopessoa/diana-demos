/*
 * Copyright (c) 2017 Otávio Santana and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Otavio Santana
 *
 */

package org.jnosql.diana.demo.couchbase;


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
            DocumentEntity entitySaved = collectionManager.insert(entity);
            Optional<Document> name = entitySaved.find("name");

            JsonObject params = JsonObject.create();
            params.put("name", name.get().get());
            List<DocumentEntity> entities = collectionManager.n1qlQuery("select * from " + DATABASE + " where name = $name", params);
            System.out.println(entities);


        }
    }
}
