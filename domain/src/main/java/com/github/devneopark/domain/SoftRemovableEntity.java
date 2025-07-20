package com.github.devneopark.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SoftRemovableEntity<ID> extends BaseEntity<ID> {

    protected Instant removedAt;

    public void remove() {
        if (this.removedAt == null) {
            this.removedAt = Instant.now();
        }
    }

    public boolean isRemoved() {
        return removedAt != null;
    }

}
