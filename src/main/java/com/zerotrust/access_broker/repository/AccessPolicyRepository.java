package com.zerotrust.access_broker.repository;

import com.zerotrust.access_broker.model.AccessPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessPolicyRepository extends JpaRepository<AccessPolicy, Long> {
}
