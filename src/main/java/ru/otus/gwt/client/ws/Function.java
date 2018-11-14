package ru.otus.gwt.client.ws;

import com.google.gwt.core.client.JavaScriptObject;
import jsinterop.annotations.JsFunction;

@JsFunction
public interface Function {
    JavaScriptObject call(JavaScriptObject event);
}
