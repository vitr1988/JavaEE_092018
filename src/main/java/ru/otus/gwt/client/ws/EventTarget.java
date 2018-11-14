package ru.otus.gwt.client.ws;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative=true, namespace= JsPackage.GLOBAL)
public class EventTarget {
    public native void addEventListener(String type, Function listener);
}
