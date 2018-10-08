package ru.otus.web;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
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
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); // the same
        ScriptEngine engine = factory.getScriptEngine(new String[] { "-scripting" });

        Bindings bindings = engine.createBindings();
        bindings.put("count", 3);
        bindings.put("name", "Vitalii");

        try(PrintWriter pw = response.getWriter()){
            String programForExecution = request.getParameter("text");
            pw.println(engine.eval(programForExecution, bindings));
            Invocable invocable = (Invocable) engine;
            engine.eval("function composeGreeting(name) {" +
                "return 'Hello ' + name" +
            "}");
            pw.println(invocable.invokeFunction("composeGreeting", "Vitalii"));
        }
        catch(ScriptException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //var greeting='hello world';print(greeting);greeting
        //readLine("What is your name? ")
        //$EXEC("notepad")
        //$ENV.PWD

    }
}
