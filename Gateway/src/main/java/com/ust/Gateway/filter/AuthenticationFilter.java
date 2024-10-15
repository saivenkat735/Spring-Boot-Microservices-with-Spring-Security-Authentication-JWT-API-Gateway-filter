package com.ust.Gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
        // Put your configuration properties here
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                // check if the exchange request header contains the Authorization header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing Authorization Header");
                }
                // take out the AUthorization header
                String authHeaderToken = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeaderToken != null && authHeaderToken.startsWith("Bearer")) {
                    // remove Bearer from front
                    authHeaderToken = authHeaderToken.substring(7);
                }

                try {
                    // now consume /api/auth/validate/token of authentication-service using
                    // RestClient
                    // can keep this call in a seperate JwtUtil class and call
                    RestClient restClient = RestClient.create();
                    restClient
                            .get()
                            .uri("http://localhost:9093/api/auth/validate/token?token=" + authHeaderToken)
                            .retrieve()
                            .body(Boolean.class);
                }
                // .toBodilessEntity();
                catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("Inavlid Access!! : " + e.getMessage());
            }

                // Perform authentication logic
                // If authentication fails, return an error response
                // Otherwise, proceed with the request
            }

            return chain.filter(exchange);
        });
    }

}
