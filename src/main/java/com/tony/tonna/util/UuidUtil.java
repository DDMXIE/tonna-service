package com.tony.tonna.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidUtil {

    public String createUUid() {
//        for (int i = 0; i < 36; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
//        }
        return uuid;
    }
}
