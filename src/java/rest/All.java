package rest;

import com.google.gson.Gson;
import deploy.DeploymentConfiguration;
import entity.Role;
import entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import javax.ws.rs.core.MediaType;
import security.PasswordHash;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import xml.XmlReaderDemo;

@Path("add")
public class All {

    @GET
    @Path("{username}/{password}/{role}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething(@PathParam("username") String userName,
            @PathParam("password") String password,
            @PathParam("role") String role) throws NoSuchAlgorithmException, InvalidKeySpecException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        EntityManager em = emf.createEntityManager();

        Role roleGiven = em.find(Role.class, role);
        User u = new User(userName, PasswordHash.createHash(password));
        u.AddRole(roleGiven);

        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return "{'text' : 'user " + userName + " was successfully created !'} ";
        } catch (Exception e) {

        } finally {
            em.close();
            emf.close();
        }
        return "{'text' : 'Something whent wrong, please try again ! '}";
    }

    @GET
    public String getXml() throws NoSuchAlgorithmException, InvalidKeySpecException, MalformedURLException, ProtocolException, IOException, Exception {

        
        Gson g = new Gson();
        
        //print result
        return g.toJson(new XmlReaderDemo().call());
    }

   
}
