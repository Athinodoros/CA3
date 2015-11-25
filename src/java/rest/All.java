package rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("add")
public class All {
  
  @GET
  @Path("{username}/{password}/{role}")
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(@PathParam ("username") String userName ,
                            @PathParam ("password") String password ,
                            @PathParam  ("role") String role) {
      
      
      
      
      
   return "";
  }
 
}