package com.Management.ProductListing.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
public class UuidConversion {
    public UuidConversion() {
    }

    public String StringToUUID(String id){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(id.getBytes());
            // Convert the byte array to a UUID-like format
            long mostSigBits = 0;
            long leastSigBits = 0;
            for (int i = 0; i < 8; i++) {
                mostSigBits = (mostSigBits << 8) | (hash[i] & 0xff);
                leastSigBits = (leastSigBits << 8) | (hash[i + 8] & 0xff);
            }
            UUID uuid = new UUID(mostSigBits, leastSigBits);
            System.out.println("UUID-like representation from 'qwer123': " + uuid);
            return uuid.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
