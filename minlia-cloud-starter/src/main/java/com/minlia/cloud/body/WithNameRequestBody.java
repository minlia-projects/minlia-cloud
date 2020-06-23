package com.minlia.cloud.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Created by will on 6/20/17.
 */
@ApiModel(value = "带名称的请求体")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WithNameRequestBody implements Body {
    @ApiModelProperty(value = "名称")
    @JsonProperty
    private String name;
}
