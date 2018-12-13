package ru.otus.ejb.session.async;

import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;

@WebServlet(urlPatterns = "/asyncEJB", asyncSupported = true)
public class AsyncConsumerEJBServlet extends HttpServlet {

    @EJB
    private AsyncApplicationBean service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter pw = response.getWriter();
        pw.write("<html><body>Started publishing with thread " + Thread.currentThread().getId() + "<br>");
        response.flushBuffer(); // send back to the browser NOW
        CompletableFuture<String> cf = new CompletablePromise<>(service.helloWorld());
        // since we need to keep the response open, we need to start an async context
        final AsyncContext ctx = request.startAsync(request, response);
        cf.whenCompleteAsync((s, t) -> {
            try {
                if (t != null) throw t;
                pw.write("written in the future using thread " + Thread.currentThread().getId()
                        + "... service response is:");
                pw.write(s);
                pw.write("</body></html>");
                response.flushBuffer();
                ctx.complete(); // all done, free resources
            } catch (Throwable t2) {

            }
        });
    }

    class CompletablePromise<V> extends CompletableFuture<V> {
        private Future<V> future;

        public CompletablePromise(Future<V> future) {
            this.future = future;
            CompletablePromiseContext.schedule(this::tryToComplete);
        }

        private void tryToComplete() {
            if (future.isDone()) {
                try {
                    complete(future.get());
                } catch (InterruptedException e) {
                    completeExceptionally(e);
                } catch (ExecutionException e) {
                    completeExceptionally(e.getCause());
                }
                return;
            }

            if (future.isCancelled()) {
                cancel(true);
                return;
            }

            CompletablePromiseContext.schedule(this::tryToComplete);
        }
    }

    static class CompletablePromiseContext {
        private static final ScheduledExecutorService SERVICE = Executors.newSingleThreadScheduledExecutor();

        public static void schedule(Runnable r) {
            SERVICE.schedule(r, 1, TimeUnit.MILLISECONDS);
        }
    }
}
