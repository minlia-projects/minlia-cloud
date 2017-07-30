package com.minlia.cloud.aop.common;

import java.util.UUID;

/**
 * Interface to mark objects associated with a user.
 */
public interface IdentifiableUser {

    UUID getUserUuid();

}
