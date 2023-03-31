package com.lesein.gateway.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangJie
 * @date 2023/3/31
 */
@Component
public class GatewayConfigService implements ApplicationEventPublisherAware, CommandLineRunner {

    private ApplicationEventPublisher publisher;

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void loadRouteConfig() {
        RouteDefinition definition = new RouteDefinition();
        Map<String, String> predicateParams = new HashMap<>(8);
        PredicateDefinition predicate = new PredicateDefinition();
        FilterDefinition filterDefinition = new FilterDefinition();
        Map<String, String> filterParams = new HashMap<>(8);

        URI uri =null;
        uri  =UriComponentsBuilder.fromUriString("lb://authorization").build().toUri();
//        uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8763/authorization").build().toUri();
        // 定义的路由唯一的id
        definition.setId("authorization");
        predicate.setName("Path");
        // 路由转发地址
        predicateParams.put("pattern", "/auth/**");
        predicate.setArgs(predicateParams);

        // 名称是固定的, 路径去前缀
        filterDefinition.setName("StripPrefix");
        filterParams.put("_genkey_0", "1");
        filterDefinition.setArgs(filterParams);
        definition.setPredicates(Collections.singletonList(predicate));
        definition.setFilters(Collections.singletonList(filterDefinition));
        definition.setUri(uri);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void run(String... args) throws Exception {
        this.loadRouteConfig();
    }
}
