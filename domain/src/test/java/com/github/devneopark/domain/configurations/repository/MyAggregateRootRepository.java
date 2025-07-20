package com.github.devneopark.domain.configurations.repository;

import com.github.devneopark.domain.configurations.model.entity.MyAggregateRoot;
import com.github.devneopark.domain.configurations.model.vo.MyAggregateRootId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MyAggregateRootRepository extends JpaRepository<MyAggregateRoot, MyAggregateRootId> {

    @Query("select ag from MyAggregateRoot ag left join fetch ag.myFooEntities where ag.id = :id")
    Optional<MyAggregateRoot> findWithFooById(MyAggregateRootId id);

    @Query("select ag from MyAggregateRoot ag left join fetch ag.myBarEntities where ag.id = :id")
    Optional<MyAggregateRoot> findWithBarById(MyAggregateRootId id);

}
