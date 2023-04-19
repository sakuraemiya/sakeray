package com.lesein.gateway.url.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.lesein.gateway.url.request.GatewayUrlRequest;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangJie
 * @date 2023/4/10
 */
@Service
public class GatewayUrlService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayUrlService.class);

    @Resource
    private GatewayUrlCommonService gatewayUrlCommonService;

    /**
     * 新增路由
     *
     * @param request
     */
    public void addUrl(List<GatewayUrlRequest> request) {
        if (!request.isEmpty()) {
            request.forEach(item -> {
                RouteDefinition definition = new RouteDefinition();
                Map<String, String> predicateParams = new HashMap<>(8);
                PredicateDefinition predicate = new PredicateDefinition();
                FilterDefinition filterDefinition = new FilterDefinition();
                Map<String, String> filterParams = new HashMap<>(8);

                URI uri;
                uri = UriComponentsBuilder.fromUriString("lb://" + item.getUniqueId()).build().toUri();
                // 定义的路由唯一的id
                definition.setId(item.getUniqueId());
                predicate.setName("Path");
                // 路由转发地址
                predicateParams.put("pattern", item.getPath());
                predicate.setArgs(predicateParams);

                // 名称是固定的, 路径去前缀
                filterDefinition.setName("StripPrefix");
                filterParams.put("_genkey_0", "1");
                filterDefinition.setArgs(filterParams);
                definition.setPredicates(Collections.singletonList(predicate));
                definition.setFilters(Collections.singletonList(filterDefinition));
                definition.setUri(uri);
                gatewayUrlCommonService.add(definition);
            });
        }
    }
}
