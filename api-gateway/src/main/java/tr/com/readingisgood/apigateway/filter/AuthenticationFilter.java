package tr.com.readingisgood.apigateway.filter;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import tr.com.readingisgood.apigateway.authconfig.JwtTokenUtil;
import tr.com.readingisgood.apigateway.authconfig.SecurityConstants;
import tr.com.readingisgood.apigateway.exception.BadCredentialsException;
import tr.com.readingisgood.apigateway.model.domain.User;
import tr.com.readingisgood.apigateway.service.UserService;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authenticatedUserId = StringUtils.EMPTY;
            try {
                authenticatedUserId = doFilterInternal(exchange.getRequest());
            } catch (Exception e) {
                logger.error("Exception was caught", e);
                return onError(exchange, "Authorization fault!", HttpStatus.UNAUTHORIZED);
            }
            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .header("authenticated-userid", authenticatedUserId)
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
            //request.mutate().header("AuthenticatedUserId", user.getId());
        };
    }

    protected String doFilterInternal(ServerHttpRequest request) {

        HttpHeaders requestHeaders = request.getHeaders();
        List<String> tokenHeaders = requestHeaders.get(SecurityConstants.HEADER_STRING);

        String username = null;
        String jwtToken = null;

        if (CollectionUtils.isNotEmpty(tokenHeaders) && tokenHeaders.stream().anyMatch(tokenHeader -> tokenHeader.startsWith(SecurityConstants.TOKEN_PREFIX))) {
            jwtToken = tokenHeaders.stream().findFirst().get().substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.warn("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.warn("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        if (username != null) {
            User user = userService.getUserByUserName(username);
            if (jwtTokenUtil.validateToken(jwtToken, user)) {
                return user.getId();
            }
        }

        logger.warn("JWT Token does not begin with Bearer String");
        throw new BadCredentialsException("Unauthorized");
    }

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }


}
