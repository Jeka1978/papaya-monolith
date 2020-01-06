package com.papaya.infra.rrmistarter;

import com.papaya.infra.rrmistarter.common.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author Evgeny Borisov
 */
@Controller
public class ControllerRegistry {
    @Autowired
    private RequestMappingHandlerMapping mapping;

    @Autowired
    private GenericApplicationContext context;

    @EventListener(ContextRefreshedEvent.class)
    public void registerControllers() {
        Collection<Adapter> adapters = context.getBeansOfType(Adapter.class).values();
        for (Adapter adapter : adapters) {
            for (Method method : adapter.getClass().getDeclaredMethods()) {

                RequestMappingInfo requestMappingInfo = RequestMappingInfo
                        .paths("generated/"+method.getName())
                        .methods(RequestMethod.GET)
                        .produces(MediaType.APPLICATION_JSON_VALUE)
                        .build();

                mapping.
                        registerMapping(requestMappingInfo, adapter,
                                method
                        );

              /*  mapping.registerMapping(
                        RequestMappingInfo.paths("/generated/" + method.getName()).methods(RequestMethod.GET)
                                .produces(MediaType.APPLICATION_JSON_VALUE).build(),
                        adapter,
                        // Method to be executed when above conditions apply, i.e.: when HTTP
                        // method and URL are called)
                        method);*/
            }

        }
    }

}
