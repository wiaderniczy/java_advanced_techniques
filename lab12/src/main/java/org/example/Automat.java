package org.example;

import org.graalvm.polyglot.*;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Automat {
    private Context context;
    private Value binding;

    public Automat(String script){
        loadScript(script);
    }

    public void loadScript(String script){
        clearContext();
        try {
            context = Context.newBuilder("js").allowAllAccess(true).build();
            context.eval("js", new String(Files.readAllBytes(Paths.get(script))));
            binding = context.getBindings("js");
        } catch (Exception e){}
    }

    public Object callFunction(String function, Object... args) {
        try {
            Value jsFunction = binding.getMember(function);
            return jsFunction.execute(args).as(int[][].class);
        } catch (Exception e) {}
        return null;
    }

    public void clearContext(){
        if(context != null){
            context.close();
            context = null;
            binding = null;
        }
    }
}
