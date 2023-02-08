package io.quarkus.ts.openshift.security.keycloak;

import io.quarkus.oidc.client.OidcClient;
import io.quarkus.oidc.client.OidcClients;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/generate-token")
public class TokenProviderResource {
    @Inject
    OidcClient defaultOidcClient;

    @Inject
    OidcClients allOidcClients;

    @GET
    @Path("/client-credentials")
    public String getTokenUsingClientCredentialsGrant() {
        // By default, it's configured using client credentials
        return generateToken(defaultOidcClient);
    }

    @GET
    @Path("/jwt-secret")
    public String getTokenUsingJwtSecret() {
        return generateToken(allOidcClients.getClient("jwt-secret"));
    }

    @GET
    @Path("/normal-user-password")
    public String getTokenUsingNormalUserPasswordGrant() {
        return generateToken(allOidcClients.getClient("normal-user"));
    }

    @GET
    @Path("/admin-user-password")
    public String getTokenUsingAdminUserPasswordGrant() {
        return generateToken(allOidcClients.getClient("admin-user"));
    }

    private String generateToken(OidcClient client) {
        return client.getTokens().await().indefinitely().getAccessToken();
    }
}
