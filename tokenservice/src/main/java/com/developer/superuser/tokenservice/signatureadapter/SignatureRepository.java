package com.developer.superuser.tokenservice.signatureadapter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureRepository extends JpaRepository<SignatureEntity, Long> {
}