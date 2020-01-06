package com.papaya.infra.rrmistarter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.papaya.infra.rrmistarter.common.Adapter;
import com.papaya.infra.rrmistarter.common.Pair;
import com.papaya.infra.rrmistarter.common.Person;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeny Borisov
 */
@RestController
@RequestMapping("/api/generated/")
@DependsOn("adapterImplGenerator")
public class ControllerRegistry {
    @Autowired
    private RequestMappingHandlerMapping mapping;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GenericApplicationContext context;

    private Map<String, Pair<Method,Adapter>> map = new HashMap<>();

    @GetMapping("{methodId}")
    @SneakyThrows
    public Object restInvocation(@PathVariable String methodId){
        Method method = map.get(methodId)._1();
        Adapter adapter = map.get(methodId)._2();
        return method.invoke(adapter);
    }

    @PostMapping("{methodId}")
    @SneakyThrows
    public Object restInvocation(@PathVariable String methodId,@RequestBody String body){
        Pair<Method, Adapter> pair = map.get(methodId);
        if (pair == null) {
            throw new UnsupportedOperationException("remote method " + methodId + " was not registered in controller");
        }
        Method method = pair._1();
        Adapter adapter = pair._2();
        Object o = objectMapper.readValue(body, method.getParameterTypes()[0]);
        return method.invoke(adapter,o);
    }


    @EventListener(ContextRefreshedEvent.class)
    public void registerControllers() {
        System.out.println("endpoints mapping started");
        Collection<Adapter> adapters = context.getBeansOfType(Adapter.class).values();
        for (Adapter adapter : adapters) {
            for (Method method : adapter.getClass().getDeclaredMethods()) {

                map.put(method.getName(), new Pair<>(method, adapter));
            }

        }
    }

}
