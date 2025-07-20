package com.github.devneopark.domain.configurations.model.entity;

import com.github.devneopark.domain.AggregateRoot;
import com.github.devneopark.domain.configurations.model.vo.MyAggregateRootId;
import com.github.devneopark.domain.configurations.model.vo.MyBarEntityId;
import com.github.devneopark.domain.configurations.model.vo.MyFooEntityId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MyAggregateRoot extends AggregateRoot<MyAggregateRootId> {

    @EmbeddedId
    private MyAggregateRootId id;

    @Builder.Default
    @OneToMany(mappedBy = "root", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MyFooEntity> myFooEntities = new ArrayList<>();

    @Transient
    private Map<MyFooEntityId, MyFooEntity> myFooCache;

    @Builder.Default
    @OneToMany(mappedBy = "root", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MyBarEntity> myBarEntities = new ArrayList<>();

    @Transient
    private Map<MyBarEntityId, MyBarEntity> myBarCache;

    @Override
    protected void afterLoad() {
        setMyFooCache();
        setMyBarCache();
    }

    @Override
    protected void afterPersist() {
        setMyFooCache();
        setMyBarCache();
    }

    public void addMyFooEntity(MyFooEntity myFooEntity) {
        this.myFooEntities.add(myFooEntity);
        this.myFooCache.put(myFooEntity.getId(), myFooEntity);
    }

    public Optional<MyFooEntity> getMyFooEntity(MyFooEntityId id) {
        Optional<MyFooEntity> foo = Optional.ofNullable(this.myFooCache.get(id));
        if (foo.isEmpty()) {
            return this.myFooEntities.stream()
                    .filter(entity -> Objects.equals(entity.getId(), id))
                    .findFirst();
        }
        return foo;
    }

    public void removeMyFooEntity(MyFooEntityId id) {
        this.getMyFooEntity(id)
                .ifPresent(MyFooEntity::remove);
    }

    public void addMyBarEntity(MyBarEntity myBarEntity) {
        this.myBarEntities.add(myBarEntity);
        this.myBarCache.put(myBarEntity.getId(), myBarEntity);
    }

    public Optional<MyBarEntity> getMyBarEntity(MyBarEntityId id) {
        Optional<MyBarEntity> bar = Optional.ofNullable(this.myBarCache.get(id));
        if (bar.isEmpty()) {
            return this.myBarEntities.stream()
                    .filter(entity -> Objects.equals(entity.getId(), id))
                    .findFirst();
        }
        return bar;
    }

    public void removeMyBarEntity(MyBarEntityId id) {
        this.getMyBarEntity(id)
                .ifPresent(MyBarEntity::remove);
    }

    private void setMyFooCache() {
        if (Hibernate.isInitialized(this.myFooEntities)) {
            if (myFooCache == null) {
                myFooCache = new HashMap<>();
            }
            myFooEntities.forEach(foo -> {
                myFooCache.put(foo.getId(), foo);
            });
        }
    }

    private void setMyBarCache() {
        if (Hibernate.isInitialized(this.myBarEntities)) {
            if (myBarCache == null) {
                myBarCache = new HashMap<>();
            }
            myBarEntities.forEach(bar -> {
                myBarCache.put(bar.getId(), bar);
            });
        }
    }

}
