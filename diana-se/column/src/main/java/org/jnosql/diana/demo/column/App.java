package org.jnosql.diana.demo.column;


import org.jnosql.diana.api.TTL;
import org.jnosql.diana.api.column.*;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;

import java.util.Arrays;
import java.util.List;

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
            columnEntityManager.saveAsync(entity, TTL.ofHours(5));

            ColumnQuery query = ColumnQuery.of(COLUMN_FAMILY);
            query.addCondition(ColumnCondition.eq(id));
            List<ColumnEntity> columnFamilyEntities = columnEntityManager.find(query);
            System.out.println(columnFamilyEntities);

        }


    }
}
