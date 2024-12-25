package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleWebAppTest {

    @Test
    public void testSum() {
        int result = sum(2, 3);
        assertEquals(5, result, "Sum should be 5");
    }

    @Test
    public void testAppName() {
        String appName = getAppName();
        assertTrue(appName.contains("Sample"), "App name should contain 'Sample'");
    }

    // Sample methods to test
    private int sum(int a, int b) {
        return a + b;
    }

    private String getAppName() {
        return "Sample Web Application";
    }
}
