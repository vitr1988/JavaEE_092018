package ru.otus.webservice.rest;

import org.apache.log4j.Logger;
import ru.otus.webservice.rest.model.Report;

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
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Path("reports")
public class ReportResource {

    private static final Logger logger = Logger.getLogger(ReportResource.class.getName());

    @Resource
    private ManagedScheduledExecutorService executor;

    // all reports
    List<Report> reports = Arrays.asList(
            new Report(1, "Annual report", "This is report which is conducted every year")
            , new Report(2, "Monthly report", "This is report which is conducted every month")
    );

    @GET
    @Path("/{id}")
    public void getReportDescription(@PathParam("id") int id, @Suspended AsyncResponse async) {
        async.setTimeout(2000, TimeUnit.MILLISECONDS);
        async.setTimeoutHandler(new CancelTimeoutHandlerImpl());
        // Optionally, send request to executor queue for processing
        executor.execute(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Retrieve the report data for 'id'
            // which is presumed to be a very slow, blocking operation
            Report report = reports.stream().filter(r -> r.getId() == id)
                    .findFirst().orElse(null);
            // Re-activate the client connection with 'resume'
            // and send the 'reportdata' object as the response
            async.resume(
                report == null ?
                    Response.ok("Noting to show:(", MediaType.TEXT_PLAIN).build() :
                        Response.ok(report, MediaType.APPLICATION_JSON_TYPE).build());
        });
    }

    @GET
    @Path("/all")
    public void getAll(@Suspended final AsyncResponse ar) {
        ar.register(new CompletionCallbackImpl());
        ar.setTimeout(6000, TimeUnit.MILLISECONDS);
        executor.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ar.resume(Response.ok(reports, MediaType.APPLICATION_JSON_TYPE).build());
        });
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
            if (t == null) {
                logger.info("All reports were sending successfully");
            }
            else {
                logger.error("During fetching all reports exception happened: " + t.getLocalizedMessage());
            }
        }
    }

    private class CancelTimeoutHandlerImpl implements TimeoutHandler {

        @Override
        public void handleTimeout(AsyncResponse asyncResponse) {
            asyncResponse.cancel();
        }

    }
}
