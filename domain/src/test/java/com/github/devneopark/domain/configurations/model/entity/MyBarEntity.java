package com.github.devneopark.domain.configurations.model.entity;

import com.github.devneopark.domain.SoftRemovableEntity;
import com.github.devneopark.domain.configurations.model.vo.MyBarEntityId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MyBarEntity extends SoftRemovableEntity<MyBarEntityId> {

    @EmbeddedId
    private MyBarEntityId id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_aggregate_root_id")
    private MyAggregateRoot root;

    void allocateRoot(MyAggregateRoot myAggregateRoot) {
        if (this.root == null) {
            this.root = myAggregateRoot;
        }
    }

}
