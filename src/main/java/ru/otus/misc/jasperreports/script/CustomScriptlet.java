package ru.otus.misc.jasperreports.script;

import net.sf.jasperreports.engine.JRDefaultScriptlet;

public class CustomScriptlet extends JRDefaultScriptlet {
    public CustomScriptlet() {
    }

    public String helloWorld() {
        return "Hello World from scriptlet";
    }
}
