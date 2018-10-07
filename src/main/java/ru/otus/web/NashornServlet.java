package ru.otus.web;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/js")
public class NashornServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        ScriptEngine engine = factory.getScriptEngine(new String[] { "-scripting" });

        try(PrintWriter pw = response.getWriter()){
            pw.println(engine.eval(request.getParameter("text")));
        }
        catch(ScriptException e){
            e.printStackTrace();
        }
        //$EXEC("notepad")
        //$ENV.PWD
        /* var name = readLine("What is your name? ")
        print("Hello, ${name}!")*/
    }
}
