package org.jnosql.diana.demo.column;


import com.datastax.driver.core.ConsistencyLevel;
import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnEntity;
import org.jnosql.diana.api.column.ColumnQuery;
import org.jnosql.diana.cassandra.column.CassandraColumnFamilyManager;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;
import org.jnosql.diana.cassandra.column.CassandraDocumentEntityManagerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CassandraApp {


    public static final String KEY_SPACE = "newKeySpace";
    public static final String COLUMN_FAMILY = "newColumnFamily";

    public static void main(String[] args) {

        CassandraConfiguration condition = new CassandraConfiguration();

        try(CassandraDocumentEntityManagerFactory managerFactory = condition.get()) {
            CassandraColumnFamilyManager columnEntityManager = managerFactory.get(KEY_SPACE);
            ColumnEntity entity = ColumnEntity.of(COLUMN_FAMILY);
            Column id = Column.of("id", 10L);
            entity.add(id);
            entity.add(Column.of("version", 0.001));
            entity.add(Column.of("name", "Diana"));
            entity.add(Column.of("options", Arrays.asList(1, 2, 3)));

            columnEntityManager.save(entity);

            //common implementation
            ColumnQuery query = ColumnQuery.of(COLUMN_FAMILY);
            query.and(ColumnCondition.eq(id));
            Optional<ColumnEntity> result = columnEntityManager.singleResult(query);

            //cassandra implementation
            columnEntityManager.save(entity, ConsistencyLevel.THREE);
            List<ColumnEntity> entities = columnEntityManager.cql("select * from newKeySpace.newColumnFamily");
            System.out.println(entities);

        }


    }

}
