package dev.lydtech.tracking.util;

import java.util.UUID;

import dev.lydtech.dispatch.message.DispatchPreparing;

public class TestEventData {

    public static DispatchPreparing buildDispatchPreparingEvent(UUID orderId) {
        return DispatchPreparing.builder()
                .orderId(orderId)
                .build();
    }
}
