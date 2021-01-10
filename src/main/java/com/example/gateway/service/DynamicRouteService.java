//package com.example.gateway.service;
//
//import com.example.gateway.compoent.RedisRouteDefinitionRepository;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//
///**
// * @ClassName：DynamicRouteService
// * @Description：
// * @Author：Peng Liu
// * @Date：2020/11/16
// * @Version：V1.0
// **/
//@Service
//public class DynamicRouteService implements ApplicationEventPublisherAware {
//    @Resource
//    private RedisRouteDefinitionRepository redisRouteDefinitionRepository;
//
//    private ApplicationEventPublisher publisher;
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.publisher = applicationEventPublisher;
//    }
//
//    private void notifyChanged() {
//        this.publisher.publishEvent(new RefreshRoutesEvent(this));
//    }
//
//
//    public Flux<RouteDefinition> getRouteDefinitions() {
//        return redisRouteDefinitionRepository.getRouteDefinitions();
//    }
//
//    /**
//     * 增加路由
//     *
//     */
//    public String add(RouteDefinition definition) {
//        redisRouteDefinitionRepository.save(Mono.just(definition)).subscribe();
//        notifyChanged();
//        return "success";
//    }
//
//
//    /**
//     * 更新路由
//     */
//    public String update(RouteDefinition definition) {
//        try {
//            this.redisRouteDefinitionRepository.delete(Mono.just(definition.getId()));
//        } catch (Exception e) {
//            return "update fail,not find route  routeId: " + definition.getId();
//        }
//        try {
//            redisRouteDefinitionRepository.save(Mono.just(definition)).subscribe();
//            notifyChanged();
//            return "success";
//        } catch (Exception e) {
//            return "update route  fail";
//        }
//
//
//    }
//
//    /**
//     * 删除路由
//     *
//     */
//    public String delete(String id) {
//        try {
//            this.redisRouteDefinitionRepository.delete(Mono.just(id)).subscribe();
//            notifyChanged();
//            return "delete success";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "delete fail";
//        }
//
//    }
//
//
//}
