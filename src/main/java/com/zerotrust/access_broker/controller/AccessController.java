package com.zerotrust.access_broker.controller;

import com.zerotrust.access_broker.model.AccessRequest;
import com.zerotrust.access_broker.model.AccessResponse;
import com.zerotrust.access_broker.policy.PolicyEngine;
import com.zerotrust.access_broker.service.AccessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {
    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping("/access")
    public ResponseEntity<AccessResponse> checkAccess(
            Authentication authentication,
            @RequestParam String service) {
        AccessRequest request = new AccessRequest(authentication.getName(), service);
        AccessResponse response = accessService.checkAccess(request);
        HttpStatus status = PolicyEngine.ALLOW.equals(response.getDecision())
                ? HttpStatus.OK
                : HttpStatus.FORBIDDEN;

        return ResponseEntity.status(status).body(response);
    }
}
