package com.developer.superuser.tokenservice.signatureadapter;

import com.developer.superuser.tokenservice.TokenserviceConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SignatureRepository extends JpaRepository<SignatureEntity, Long> {
    @Query(value = TokenserviceConstant.FIND_BY_REQUEST_ID, nativeQuery = true)
    Optional<SignatureEntity> findByRequestId(String requestId);
}