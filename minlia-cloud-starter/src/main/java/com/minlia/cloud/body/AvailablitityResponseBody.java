package com.minlia.cloud.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minlia.cloud.annotation.i18n.Localized;
import com.minlia.cloud.constant.ValidationConstants;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Created by will on 6/22/17.
 * 可用性验证请求体
 */
@Data
@Localized
public class AvailablitityResponseBody {

  private Boolean available;
  private String message;

}
