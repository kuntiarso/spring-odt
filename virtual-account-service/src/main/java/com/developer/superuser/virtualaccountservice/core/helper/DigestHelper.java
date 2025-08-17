package com.developer.superuser.virtualaccountservice.core.helper;

import com.developer.superuser.shared.helper.Generator;
import com.developer.superuser.virtualaccountservice.VirtualAccountServiceConstant;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public class DigestHelper implements Generator<String, String> {
    @Override
    @SneakyThrows
    public String generate(String request) {
        MessageDigest md = MessageDigest.getInstance(VirtualAccountServiceConstant.ALGORITHM_DIGEST_SHA256);
        md.update(request.getBytes(StandardCharsets.UTF_8));
        return toHexLowerCase(md.digest());
    }

    private static String toHexLowerCase(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}