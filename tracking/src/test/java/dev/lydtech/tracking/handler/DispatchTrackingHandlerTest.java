package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import dev.lydtech.tracking.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DispatchTrackingHandlerTest {

    private TrackingService trackingServiceMock;

    private DispatchTrackingHandler handler;

    @BeforeEach
    public void setup() {
        trackingServiceMock = mock(TrackingService.class);
        handler = new DispatchTrackingHandler(trackingServiceMock);
    }

    @Test
    void listen_Success() throws Exception {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(UUID.randomUUID());
        handler.listen(testEvent);
        verify(trackingServiceMock).process(testEvent);
    }

    @Test
    void listen_ServiceThrowsException() throws Exception {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(UUID.randomUUID());
        doThrow(new RuntimeException("Service failure")).when(trackingServiceMock).process(testEvent);

        handler.listen(testEvent);

        verify(trackingServiceMock).process(testEvent);
    }
}
