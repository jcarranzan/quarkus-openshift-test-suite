package io.quarkus.ts.openshift.security.jwt.cookie;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RolesAllowed("admin")
@Path("/permitted")
public class PermittedResource {
    @GET
    @PermitAll
    public String permitted() {
        return "Hello there!";
    }
}
