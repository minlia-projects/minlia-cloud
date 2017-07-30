package com.minlia.cloud.aop.common;

import java.util.UUID;

/**
 * Interface to mark objects associated with a request.
 */
public interface IdentifiableRequest {

    UUID getRequestUuid();

}
