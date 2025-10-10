package com.example.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {
  private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    log.info("--> Request: {} {}",
        exchange.getRequest().getMethod(),
        exchange.getRequest().getURI());

    Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    if (route != null) {
      System.out.println("Request matched route: " + route.getId());
    } else {
      System.out.println("Request did not match any route.");
    }

    return chain
        .filter(exchange)
        .then(Mono.fromRunnable(() -> log.info("<-- Response: {}",
            exchange.getResponse().getStatusCode())));
  }

  @Override
  public int getOrder() {
    return -1;
  }
}