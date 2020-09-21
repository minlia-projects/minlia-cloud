package com.minlia.cloud.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Created by will on 6/23/20.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WithListItemBody<T> implements Body {
    private List<T> items;

}
