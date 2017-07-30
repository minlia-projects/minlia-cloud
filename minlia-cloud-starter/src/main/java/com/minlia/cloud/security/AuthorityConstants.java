package com.minlia.cloud.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class AuthorityConstants {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ADMIN = "ROLE_PODIUM_ADMIN";
    public static final String ORGANISATION_ADMIN = "ROLE_ORGANISATION_ADMIN";
    public static final String ORGANISATION_COORDINATOR = "ROLE_ORGANISATION_COORDINATOR";

    public static final Set<String> ORGANISATION_AUTHORITIES = new HashSet<>(Arrays.asList(
            ORGANISATION_ADMIN,
            ORGANISATION_COORDINATOR
    ));

    public static boolean isOrganisationAuthority(String name) {
        return ORGANISATION_AUTHORITIES.contains(name);
    }

}
