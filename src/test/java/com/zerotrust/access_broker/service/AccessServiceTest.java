package com.zerotrust.access_broker.service;

import com.zerotrust.access_broker.model.AccessRequest;
import com.zerotrust.access_broker.model.AccessResponse;
import com.zerotrust.access_broker.policy.PolicyEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccessServiceTest {
    private final AccessService accessService = new AccessService(new PolicyEngine());

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void usesAuthenticatedRoleToMakeDecision() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "shailaj",
                        "password123",
                        List.of(new SimpleGrantedAuthority("ROLE_DEVELOPER"))));

        AccessResponse response = accessService.checkAccess(new AccessRequest("shailaj", "github"));

        assertEquals(PolicyEngine.ALLOW, response.getDecision());
    }

    @Test
    void deniesWhenNoAuthenticatedRoleIsAvailable() {
        AccessResponse response = accessService.checkAccess(new AccessRequest("shailaj", "github"));

        assertEquals(PolicyEngine.DENY, response.getDecision());
    }
}
