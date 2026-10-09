package com.zerotrust.access_broker.service;

import com.zerotrust.access_broker.model.AccessRequest;
import com.zerotrust.access_broker.model.AccessResponse;
import com.zerotrust.access_broker.policy.PolicyEngine;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccessService {
    private final PolicyEngine policyEngine;

    public AccessService(PolicyEngine policyEngine) {
        this.policyEngine = policyEngine;
    }

    public AccessResponse checkAccess(AccessRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = authentication == null || !authentication.isAuthenticated()
                ? Set.of()
                : authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(authority -> authority.startsWith("ROLE_"))
                        .map(authority -> authority.substring("ROLE_".length()))
                        .collect(Collectors.toSet());

        String decision = policyEngine.decide(roles, request.getService());
        String reason = PolicyEngine.ALLOW.equals(decision)
                ? "User is allowed to access this service"
                : "User is not allowed to access this service";

        return new AccessResponse(request.getUser(), request.getService(), decision, reason);
    }
}
