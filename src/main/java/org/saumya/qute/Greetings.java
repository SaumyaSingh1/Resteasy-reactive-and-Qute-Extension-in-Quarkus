package org.saumya.qute;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public class Greetings {

    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance hey();
        public static native TemplateInstance bye();
    }

    @Path("/greetings/hey")
    public class HeyGreetings{

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public TemplateInstance get(@QueryParam("name")String name){
            return Templates.hey().data("name",name);
        }
    }

    @Path("/greetings/bye")
    public class ByeGreeting{

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public TemplateInstance get(@QueryParam("name")String name){
            return Templates.bye().data("name", name);
        }
    }
}
