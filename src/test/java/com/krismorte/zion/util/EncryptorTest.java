package com.krismorte.zion.util;

import static org.junit.jupiter.api.Assertions.*;

class EncryptorTest {

    String TEXT = "Text to Encrypt";

    @org.junit.jupiter.api.Test
    void encrypt() {
        String nextText = Encryptor.encrypt(TEXT);
        assertNotEquals(nextText,TEXT);
    }

    @org.junit.jupiter.api.Test
    void decrypt() {
        String nextText = Encryptor.encrypt(TEXT);
        String decryptedText = Encryptor.decrypt(nextText);
        assertEquals(TEXT,decryptedText);
    }
}