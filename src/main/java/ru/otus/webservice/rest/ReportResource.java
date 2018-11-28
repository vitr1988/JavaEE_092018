package ru.otus.webservice.rest;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("reports")
public class ReportResource {

    @Resource
    private ManagedScheduledExecutorService executor;

    @GET
    @Path("/{id}")
    public void getReportDescription(@PathParam("id") String id, @Suspended AsyncResponse async) {
        async.setTimeout(2000, TimeUnit.MILLISECONDS);
        async.setTimeoutHandler(new CancelTimeoutHandlerImpl());
        // Optionally, send request to executor queue for processing
        executor.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Retrieve the book data for 'id'
            // which is presumed to be a very slow, blocking operation
            // ...
            List<String> reportData = new ArrayList<>();
            // Re-activate the client connection with 'resume'
            // and send the 'bookdata' object as the response
            async.resume(reportData);
        });
    }

    @GET
    @Path("/all")
    public void getAll(@Suspended final AsyncResponse ar) {
        ar.register(new CompletionCallbackImpl());
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void reportComplete(
            @Context SseEventSink eventSink,
            @Context Sse sse) {
        try (SseEventSink sink = eventSink) {
            sink.send(sse.newEvent("Report is Generating"));
            sink.send(sse.newEvent("Report is still Generating", "Some data"));
            OutboundSseEvent event = sse.newEventBuilder().
                    id("ReportId").
                    name("Account Report").
                    data("Big Report Data").
                    reconnectDelay(1000).
                    comment("Your ads could be here").build();
            sink.send(event);
        }
    }

    private class CompletionCallbackImpl implements CompletionCallback {
        @Override
        public void onComplete(Throwable t) {
            //. . .
        }
    }

    private class CancelTimeoutHandlerImpl implements TimeoutHandler {

        @Override
        public void handleTimeout(AsyncResponse asyncResponse) {
            asyncResponse.cancel();
        }

    }
}
