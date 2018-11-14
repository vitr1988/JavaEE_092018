package ru.otus.gwt.client.ws;

import jsinterop.annotations.*;

@JsType(isNative=true, namespace= JsPackage.GLOBAL)
public class WebSocket extends EventTarget {
    @JsProperty
    public Function onclose;
    @JsProperty
    public Function onerror;
    @JsProperty
    public Function onmessage;
    @JsProperty
    public Function onopen;
    @JsProperty
    public String url;
    @JsConstructor
    public WebSocket(String url){}
    @JsMethod
    public native void send(String data);

    @JsMethod
    public native void close();
}
