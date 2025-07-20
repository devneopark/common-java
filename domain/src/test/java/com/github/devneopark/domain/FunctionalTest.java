package com.github.devneopark.domain;

import com.github.devneopark.domain.configurations.model.vo.MyAggregateRootId;
import com.github.devneopark.domain.configurations.model.vo.MyBarEntityId;
import com.github.devneopark.domain.configurations.model.vo.MyFooEntityId;
import com.github.devneopark.domain.configurations.repository.MyAggregateRootRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@Slf4j
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:sql/FunctionalTest.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class FunctionalTest {

    @Autowired
    MyAggregateRootRepository repository;

    @BeforeAll
    static void setup() {
        log.info("hello world!!!");
    }

    @Test
    void test1() {
        repository.findAll()
                .forEach(myAggregateRoot -> {
                    MyAggregateRootId id = myAggregateRoot.getId();
                    log.info("id: {}", id);
                    boolean isRemoved = myAggregateRoot.isRemoved();
                    log.info("is removed: {}", isRemoved);
                    boolean isFooInitialized = Hibernate.isInitialized(myAggregateRoot.getMyFooEntities());
                    log.info("is foos initialized: {}", isFooInitialized);
                    boolean isBarInitialized = Hibernate.isInitialized(myAggregateRoot.getMyBarEntities());
                    log.info("is bars initialized: {}", isBarInitialized);
                });
    }

    @Test
    void test2() {
        repository.findWithFooById(new MyAggregateRootId("ag1"))
                .ifPresent(myAggregateRoot -> {
                    MyAggregateRootId id = myAggregateRoot.getId();
                    log.info("id: {}", id);
                    boolean isRemoved = myAggregateRoot.isRemoved();
                    log.info("is removed: {}", isRemoved);
                    boolean isFooInitialized = Hibernate.isInitialized(myAggregateRoot.getMyFooEntities());
                    log.info("is foos initialized: {}", isFooInitialized);
                    boolean isBarInitialized = Hibernate.isInitialized(myAggregateRoot.getMyBarEntities());
                    log.info("is bars initialized: {}", isBarInitialized);

                    if (isFooInitialized) {
                        myAggregateRoot.getMyFooEntities().forEach(foo -> {
                            MyFooEntityId fooId = foo.getId();
                            log.info("id: {}", fooId);
                            String rootId = foo.getRoot().getId().toString();
                            log.info("root id: {}", rootId);
                            boolean isFooRemoved = foo.isRemoved();
                            log.info("is foo removed: {}", isFooRemoved);
                        });
                    }
                });
    }

    @Test
    void test3() {
        repository.findWithBarById(new MyAggregateRootId("ag1"))
                .ifPresent(myAggregateRoot -> {
                    MyAggregateRootId id = myAggregateRoot.getId();
                    log.info("id: {}", id);
                    boolean isRemoved = myAggregateRoot.isRemoved();
                    log.info("is removed: {}", isRemoved);
                    boolean isFooInitialized = Hibernate.isInitialized(myAggregateRoot.getMyFooEntities());
                    log.info("is foos initialized: {}", isFooInitialized);
                    boolean isBarInitialized = Hibernate.isInitialized(myAggregateRoot.getMyBarEntities());
                    log.info("is bars initialized: {}", isBarInitialized);

                    if (isBarInitialized) {
                        myAggregateRoot.getMyBarEntities().forEach(bar -> {
                            MyBarEntityId fooId = bar.getId();
                            log.info("id: {}", fooId);
                            String rootId = bar.getRoot().getId().toString();
                            log.info("root id: {}", rootId);
                            boolean isBarRemoved = bar.isRemoved();
                            log.info("is bar removed: {}", isBarRemoved);
                        });
                    }
                });
    }

}
