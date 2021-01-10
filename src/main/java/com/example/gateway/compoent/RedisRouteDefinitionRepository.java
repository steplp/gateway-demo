//package com.example.gateway.compoent;
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName：RedisRouteDefinitionRepository
// * @Description：
// * @Author：Peng Liu
// * @Date：2020/11/16
// * @Version：V1.0
// **/
//@Component
//public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
//    public static final String GATEWAY_ROUTES = "gateway_routes";
//
//    @Resource
//    private RedisTemplate redisTemplate;
//
//    @Override
//    public Flux<RouteDefinition> getRouteDefinitions() {
//        List<RouteDefinition> routeDefinitions = new ArrayList<>();
//        redisTemplate.opsForHash().values(GATEWAY_ROUTES).stream()
//                .forEach(routeDefinition -> routeDefinitions.add(JSON.parseObject(routeDefinition.toString(), RouteDefinition.class)));
//        return Flux.fromIterable(routeDefinitions);
//
//    }
//
//
//    @Override
//    public Mono<Void> save(Mono<RouteDefinition> route) {
//        return route.flatMap(routeDefinition -> {
//            redisTemplate.opsForHash().put(GATEWAY_ROUTES, routeDefinition.getId(), JSON.toJSONString(routeDefinition));
//            return Mono.empty();
//        });
//    }
//
//    @Override
//    public Mono<Void> delete(Mono<String> routeId) {
//        return null;
//    }
//}
