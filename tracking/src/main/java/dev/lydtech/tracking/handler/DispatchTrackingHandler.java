package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DispatchTrackingHandler {

    private final TrackingService trackingService;

    @KafkaListener(
            id = "dispatchTrackingConsumerClient",
            topics = "dispatch.tracking",
            groupId = "tracking.dispatch.tracking",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing payload) {
        log.info("Received message: payload: " + payload);
        try {
            trackingService.process(payload);
        } catch (Exception e) {
            log.error("Processing failure", e);
        }
    }

}
