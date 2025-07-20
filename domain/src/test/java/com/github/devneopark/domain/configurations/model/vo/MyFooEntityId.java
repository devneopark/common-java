package com.github.devneopark.domain.configurations.model.vo;

import com.github.devneopark.domain.BaseId;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyFooEntityId extends BaseId<String> {

    @Column(name = "my_foo_entity_id")
    private String value;

    public MyFooEntityId(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
