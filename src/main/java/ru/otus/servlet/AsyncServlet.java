package ru.otus.servlet;

import org.apache.tools.ant.filters.StringInputStream;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "AsyncServlet", urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    public static final String TIMEOUT = "timeout";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String timeout = request.getParameter(TIMEOUT);
        if (Objects.isNull(timeout)){
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try(PrintWriter pw = response.getWriter()){
                pw.println(MessageFormat.format("Paramater {0} should be declared", TIMEOUT));
            }
            return;
        }

        final int timeoutInMsc = Integer.decode(timeout);
        final AsyncContext asyncContext = request.startAsync();

//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        executor.schedule(() -> {
//            print(new StringInputStream(MessageFormat.format("Results have being calculated for {0} secs", timeout)), asyncContext);
//            return null;
//        }, timeoutInMsc, TimeUnit.MILLISECONDS);
//        executor.shutdown();
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(timeoutInMsc);
                asyncContext.getResponse().getWriter().println(MessageFormat.format("Results have being calculated for {0} miliseconds", timeout));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).thenAccept(v -> asyncContext.complete());
    }

    private void print(final InputStream largeText, final AsyncContext context) throws IOException {
        ServletOutputStream outputStream = context.getResponse().getOutputStream();
        outputStream.setWriteListener(new WriteListener() {
            @Override
            public void onWritePossible() throws IOException {
                byte[] chunk = new byte[256];
                int read = 0;
                while (outputStream.isReady() && (read = largeText.read(chunk))  != -1) {
                    outputStream.write(chunk, 0, read);
                }
                if (read == -1) {
                    context.complete();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                context.complete();
                throwable.printStackTrace();
            }
        });

    }
}
