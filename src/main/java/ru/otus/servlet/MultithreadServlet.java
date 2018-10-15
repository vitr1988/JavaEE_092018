package ru.otus.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/multithread")
public class MultithreadServlet extends HttpServlet /*implements SingleThreadModel*/{

    private ThreadLocal<HttpServletRequest> threadRequest;
    private ThreadLocal<HttpServletResponse> threadResponse;

    private ServletConfig sc = null; //not thread safe

    public void init(ServletConfig sc){
        this.sc = sc;
    }

    private void validateThreadLocalData() {
        if (threadRequest == null) {
            threadRequest = new ThreadLocal<>();
        }
        if (threadResponse == null) {
            threadResponse = new ThreadLocal<>();
        }
    }

    /**
     * Gets the <code>HttpServletRequest</code> object for the current call. It is
     * stored thread-locally so that simultaneous invocations can have different
     * request objects.
     */
    protected final HttpServletRequest getThreadLocalRequest() {
        synchronized (this) {
            validateThreadLocalData();
            return threadRequest.get();
        }
    }

    /**
     * Gets the <code>HttpServletResponse</code> object for the current call. It
     * is stored thread-locally so that simultaneous invocations can have
     * different response objects.
     */
    protected final HttpServletResponse getThreadLocalResponse() {
        synchronized (this) {
            validateThreadLocalData();
            return threadResponse.get();
        }
    }

    protected /*synchronized*/ void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String example = "This is a thread-safe string"; // all local variable are thread sage
        try {
            // Store the request & response objects in thread-local storage.
            //
            synchronized (this) {
                validateThreadLocalData();
                threadRequest.set(request);
                threadResponse.set(response);
            }

            super.service(request, response);

        } catch (Throwable e) {
            // Give a subclass a chance to either handle the exception or rethrow it
            //
            doUnexpectedFailure(e);
        } finally {
            // null the thread-locals to avoid holding request/response
            //
            threadRequest.set(null);
            threadResponse.set(null);
        }
        switch(request.getMethod()) {
            case "POST" : doPost(request, response); break;
            case "GET" : doGet(request, response); break;
        }
    }

    protected void doUnexpectedFailure(Throwable e) {
        try {
            getThreadLocalResponse().reset();
        } catch (IllegalStateException ex) {
            /*
             * If we can't reset the request, the only way to signal that something
             * has gone wrong is to throw an exception from here. It should be the
             * case that we call the user's implementation code before emitting data
             * into the response, so the only time that gets tripped is if the object
             * serialization code blows up.
             */
            throw new RuntimeException("Unable to report failure", e);
        }
    }
}
