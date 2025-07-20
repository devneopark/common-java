package com.github.devneopark.domain;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Persistable;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity<ID> implements Persistable<ID> {

    @Transient
    @Builder.Default
    protected boolean isNew = true;

    @PostLoad
    final void postLoad() {
        this.isNew = false;
        this.afterLoad();
    }

    @PostPersist
    final void postPersist() {
        this.isNew = false;
        this.afterPersist();
    }

    /**
     * Optionally overridable
     */
    void afterLoad() {}

    /**
     * Optionally overridable
     */
    void afterPersist() {}

}
