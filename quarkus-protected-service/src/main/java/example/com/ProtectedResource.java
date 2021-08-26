package example.com;

import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProtectedResource {

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/customer")
    @RolesAllowed({"test"})
    public String getCustomerJSON(@Context SecurityContext scx) {
        return "{\"path\":\"customer\",\"result\":" + "}";
    }

}
