package org.saumya.qute;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("/items")
public class ItemList {

    @Inject
    Template items;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(){
        List<Item> list= new ArrayList<>();
        list.add(new Item("Apple", new BigDecimal(80)));
        list.add(new Item("Mango", new BigDecimal(88)));
        list.add(new Item("Dairy Milk", new BigDecimal(770)));
        list.add(new Item("Grapes", new BigDecimal(76)));
        list.add(new Item("Orange", new BigDecimal("179")));
        return items.data("items", list);
    }

    //Template extensions allow to add new methods to the POJO class that will be
    //available from Template
    @TemplateExtension
    public static BigDecimal discountedPrice(Item item){
        return item.price.multiply(new BigDecimal("0.9"));
    }
}
