/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Athinodoros
 */
@Path("currency")
public class currency {

    @Context
    private UriInfo context;

    
    Gson g = new Gson();
    /**
     * Creates a new instance of currency
     */
    public currency() {
    }   

    /**
     * Retrieves representation of an instance of rest.currency
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{amount}/{fromCurrency}/{toCurrency}")
    @Produces(MediaType.APPLICATION_JSON)
    public String convert(@PathParam("amount") double amount,
            @PathParam("fromCurrency") double from,
            @PathParam("toCurrency") double to) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        Double result = (from*amount)/to;
        
        return g.toJson(result);
    }

    /**
     * PUT method for updating or creating an instance of currency
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
