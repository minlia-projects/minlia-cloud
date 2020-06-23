package com.minlia.cloud.body;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Created by will on 6/20/17.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WithIdItemBody implements Body {
    private List<WithIdBody> items;

}
