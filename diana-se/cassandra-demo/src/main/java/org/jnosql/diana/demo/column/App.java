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

package org.jnosql.diana.demo.column;


import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnConfiguration;
import org.jnosql.diana.api.column.ColumnEntity;
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.api.column.ColumnFamilyManagerFactory;
import org.jnosql.diana.api.column.ColumnQuery;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;

import java.util.Arrays;
import java.util.Optional;

public class App {

    public static final String KEY_SPACE = "newKeySpace";
    public static final String COLUMN_FAMILY = "newColumnFamily";

    public static void main(String[] args) {

        ColumnConfiguration condition = new CassandraConfiguration();

        try(ColumnFamilyManagerFactory managerFactory = condition.get()) {
            ColumnFamilyManager columnEntityManager = managerFactory.get(KEY_SPACE);
            ColumnEntity entity = ColumnEntity.of(COLUMN_FAMILY);
            Column id = Column.of("id", 10L);
            entity.add(id);
            entity.add(Column.of("version", 0.001));
            entity.add(Column.of("name", "Diana"));
            entity.add(Column.of("options", Arrays.asList(1, 2, 3)));

            columnEntityManager.insert(entity);

            ColumnQuery query = ColumnQuery.of(COLUMN_FAMILY);
            query.and(ColumnCondition.eq(id));
            Optional<ColumnEntity> result = columnEntityManager.singleResult(query);
            System.out.println(result);

        }


    }

    private App() {}
}
