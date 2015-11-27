/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entity.Currency;
import entity.DateEntity;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    @GET
    @Path("dailyrates")
    @Consumes("application/json")
    public String getDailyRates(String content) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
        
        List<DateEntity> liDates =   em.createQuery("select u from DateEntity u").getResultList();
        //int id = da.getId();
        DateEntity lastDate = new DateEntity();
        lastDate.setId(-1);
        for (DateEntity singleDate : liDates) {
            if (lastDate.getId() < singleDate.getId()) {
                lastDate.setId(singleDate.getId());
            }
        }
        List<Currency> listOfAllRates =  em.createQuery("Select c from Currency c", Currency.class).getResultList();
        List<Currency> todaysCurrencyList = new ArrayList<>();
        for (Currency rate : listOfAllRates) {
            if (rate.getDate().getId() == lastDate.getId()) {
                Currency cur = new Currency(rate.getCurrencyCode(), rate.getDescription(), rate.getRate());
                todaysCurrencyList.add(cur);
            }
        }
        
        return g.toJson(todaysCurrencyList);
    }
}
