package example.com.qrks.sec.runtime.auth;

import example.com.qrks.sec.runtime.cache.WeatherForecastService;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.security.identity.IdentityProviderManager;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.AuthenticationRequest;
import io.quarkus.security.identity.request.TokenAuthenticationRequest;
import io.quarkus.smallrye.jwt.runtime.auth.JWTAuthMechanism;
import io.quarkus.vertx.http.runtime.security.ChallengeData;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.quarkus.vertx.http.runtime.security.HttpCredentialTransport;
import io.smallrye.jwt.auth.principal.*;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;


/**
 * An AuthenticationMechanism that validates a caller
 */
@Alternative
@Priority(1)
@ApplicationScoped
public class CustomAuthMechanism implements HttpAuthenticationMechanism {

    private static final Logger LOG = LoggerFactory.getLogger(CustomAuthMechanism.class);

    protected static final String BASIC = "Basic";
    protected static final String BEARER = "Bearer";


    @Inject
    JWTAuthMechanism delegate;

    @Inject
    JWTAuthContextInfo authContextInfo;

    @Inject
    WeatherForecastService weatherForecastService;


    @Override
    public Uni<SecurityIdentity> authenticate(RoutingContext context, IdentityProviderManager identityProviderManager) {

        String cached = weatherForecastService.getDailyForecast(LocalDate.now(), "city");

        //normally authentication code goes here
        return Uni.createFrom().optional(Optional.empty());
    }


    @Override
    public Uni<ChallengeData> getChallenge(RoutingContext context) {
        ChallengeData result = new ChallengeData(
                HttpResponseStatus.UNAUTHORIZED.code(),
                HttpHeaderNames.WWW_AUTHENTICATE,
                BEARER);
        return Uni.createFrom().item(result);
    }

    @Override
    public Set<Class<? extends AuthenticationRequest>> getCredentialTypes() {
        return Collections.singleton(TokenAuthenticationRequest.class);
    }

    @Override
    public HttpCredentialTransport getCredentialTransport() {
        return new HttpCredentialTransport(HttpCredentialTransport.Type.AUTHORIZATION, BEARER);
    }

}
