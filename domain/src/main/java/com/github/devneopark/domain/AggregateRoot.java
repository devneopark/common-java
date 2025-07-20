package com.github.devneopark.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AggregateRoot<ID> extends SoftRemovableEntity<ID> {

    @Override
    abstract protected void afterLoad();

    @Override
    abstract protected void afterPersist();

}
