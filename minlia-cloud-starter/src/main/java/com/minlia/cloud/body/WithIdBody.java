package com.minlia.cloud.body;

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
public class WithIdBody implements Body {

    /**
     * ID
     */
    private Long id;

}
