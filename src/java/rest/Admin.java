package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.minidev.json.JSONObject;

@Path("demoadmin")
@RolesAllowed("Admin")
public class Admin {

    Gson g = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(deploy.DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();
         List<entity.User> usersAll = new ArrayList<>();
        try {
            em.getTransaction().begin();
            usersAll =  em.createQuery("select u from User u ").getResultList();
            em.close();
        } catch (Exception e) {
        } finally {
           // em.close();
            emf.close();
        }
        ArrayList<JsonObject> li = new ArrayList<>();
        for (entity.User u : usersAll) {
            JsonObject jo = new JsonObject();
            jo.addProperty("userName", u.getUserName());
            jo.addProperty("roles", u.getRolesAsStrings().toString());
            li.add(jo);
        }
 

        return g.toJson(li);
    }

}
