package com.zerotrust.access_broker.policy;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PolicyEngine {
    public static final String ALLOW = "ALLOW";
    public static final String DENY = "DENY";

    public String decide(Set<String> roles, String service) {
        if (roles.contains("ADMIN")) {
            return ALLOW;
        }

        if (roles.contains("DEVELOPER") && "github".equals(service)) {
            return ALLOW;
        }

        if (roles.contains("HR") && "hr-database".equals(service)) {
            return ALLOW;
        }

        return DENY;
    }
}
