package com.zerotrust.access_broker.policy;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolicyEngineTest {
    private final PolicyEngine policyEngine = new PolicyEngine();

    @Test
    void allowsDeveloperToAccessGithub() {
        assertEquals(PolicyEngine.ALLOW, policyEngine.decide(Set.of("DEVELOPER"), "github"));
    }

    @Test
    void deniesDeveloperAccessToHrDatabase() {
        assertEquals(PolicyEngine.DENY, policyEngine.decide(Set.of("DEVELOPER"), "hr-database"));
    }

    @Test
    void allowsHrToAccessHrDatabase() {
        assertEquals(PolicyEngine.ALLOW, policyEngine.decide(Set.of("HR"), "hr-database"));
    }

    @Test
    void deniesHrAccessToGithub() {
        assertEquals(PolicyEngine.DENY, policyEngine.decide(Set.of("HR"), "github"));
    }

    @Test
    void allowsAdminToAccessAnyService() {
        assertEquals(PolicyEngine.ALLOW, policyEngine.decide(Set.of("ADMIN"), "unknown-service"));
    }

    @Test
    void deniesUnknownRoleByDefault() {
        assertEquals(PolicyEngine.DENY, policyEngine.decide(Set.of("GUEST"), "github"));
    }

    @Test
    void deniesUnknownServiceByDefault() {
        assertEquals(PolicyEngine.DENY, policyEngine.decide(Set.of("DEVELOPER"), "unknown-service"));
    }
}
