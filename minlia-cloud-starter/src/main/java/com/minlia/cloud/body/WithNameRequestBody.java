package com.minlia.cloud.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created by will on 6/20/17.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WithNameRequestBody implements Body {
    @JsonProperty
    private String name;
}
