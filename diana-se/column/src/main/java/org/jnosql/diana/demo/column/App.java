package org.jnosql.diana.demo.column;


import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnConfiguration;
import org.jnosql.diana.api.column.ColumnEntity;
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.api.column.ColumnFamilyManagerFactory;
import org.jnosql.diana.api.column.ColumnQuery;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App {

    public static final String KEY_SPACE = "newKeySpace";
    public static final String COLUMN_FAMILY = "newColumnFamily";

    public static void main(String[] args) {

        ColumnConfiguration condition = new CassandraConfiguration();

        try(ColumnFamilyManagerFactory managerFactory = condition.getManagerFactory()) {
            ColumnFamilyManager columnEntityManager = managerFactory.getColumnEntityManager(KEY_SPACE);
            ColumnEntity entity = ColumnEntity.of(COLUMN_FAMILY);
            Column id = Column.of("id", 10L);
            entity.add(id);
            entity.add(Column.of("version", 0.001));
            entity.add(Column.of("name", "Diana"));
            entity.add(Column.of("options", Arrays.asList(1, 2, 3)));

            columnEntityManager.save(entity);

            columnEntityManager.saveAsync(entity, Duration.ofSeconds(20));

            ColumnQuery query = ColumnQuery.of(COLUMN_FAMILY);
            query.addCondition(ColumnCondition.eq(id));
            Optional<ColumnEntity> result = columnEntityManager.singleResult(query);
            List<ColumnEntity> columnFamilyEntities = columnEntityManager.singleResult(query);
            System.out.println(columnFamilyEntities);

        }


    }
}
