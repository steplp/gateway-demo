package com.example.gateway;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayDemoApplication {


    public static final String GATEWAY_ROUTES = "gateway_routes";

    @Autowired
    RedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(GatewayDemoApplication.class, args);

        System.out.println(111111111);

        System.out.println(3333333333333L);
        System.out.println(3333333333333L);
        System.out.println(111111);



        System.out.println();


    }

    @PostConstruct
    public void initRoutes(){

        RouteDefinition definition = new RouteDefinition();
        definition.setId("route_id");
        URI uri = UriComponentsBuilder.fromHttpUrl("https://jd.com/jd").build().toUri();
        // URI uri = UriComponentsBuilder.fromHttpUrl("http://baidu.com").build().toUri();
        definition.setUri(uri);

        //定义第一个断言
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");

        Map<String, String> predicateParams = new HashMap<>(8);
        predicateParams.put("pattern", "/jd");
        predicate.setArgs(predicateParams);

        //定义Filter
        FilterDefinition filter = new FilterDefinition();
        filter.setName("AddRequestHeader");
        Map<String, String> filterParams = new HashMap<>(8);
        //该_genkey_前缀是固定的，见org.springframework.cloud.gateway.support.NameUtils类
        filterParams.put("_genkey_0", "header");
        filterParams.put("_genkey_1", "addHeader");
        filter.setArgs(filterParams);

        FilterDefinition filter1 = new FilterDefinition();
        filter1.setName("AddRequestParameter");
        Map<String, String> filter1Params = new HashMap<>(8);
        filter1Params.put("_genkey_0", "param");
        filter1Params.put("_genkey_1", "addParam");
        filter1.setArgs(filter1Params);

        definition.setFilters(Arrays.asList(filter, filter1));
        definition.setPredicates(Arrays.asList(predicate));

        System.out.println("definition:" + JSON.toJSONString(definition));
        redisTemplate.opsForHash().put(GATEWAY_ROUTES, "key", JSON.toJSONString(definition));


    }

}
