package com.skcc.oversea.framework.transaction.tpmutil;

import org.springframework.stereotype.Component;

@Component
public class TPMUtil {

    public void initializeTPM() {
        // TPM 珥덇린??濡쒖쭅
    }

    public void cleanupTPM() {
        // TPM ?뺣━ 濡쒖쭅
    }

    public boolean isTPMAvailable() {
        // TPM ?ъ슜 媛???щ? ?뺤씤
        return true;
    }
}