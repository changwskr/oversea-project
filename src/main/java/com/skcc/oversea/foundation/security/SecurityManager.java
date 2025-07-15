package com.skcc.oversea.foundation.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityManager {

    public boolean authenticate(String username, String password) {
        // ?몄쬆 濡쒖쭅
        return true;
    }

    public boolean authorize(String username, String resource) {
        // 沅뚰븳 ?뺤씤 濡쒖쭅
        return true;
    }

    public String encrypt(String data) {
        // ?뷀샇??濡쒖쭅
        return data;
    }

    public String decrypt(String encryptedData) {
        // 蹂듯샇??濡쒖쭅
        return encryptedData;
    }
}