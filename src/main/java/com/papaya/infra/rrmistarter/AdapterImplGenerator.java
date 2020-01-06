package com.papaya.infra.rrmistarter;

import com.papaya.infra.rrmistarter.common.Adapter;
import com.papaya.infra.rrmistarter.props.PropertiesHolder;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;

/**
 * @author Evgeny Borisov
 */
public class AdapterImplGenerator {

    @Autowired
    private GenericApplicationContext context;



    public AdapterImplGenerator(PropertiesHolder propertiesHolder) {
        var mapping = new RequestMappingHandlerMapping();
//
//        var scanner = new Reflections(propertiesHolder.getPackagesToScan());
//        Arrays.stream(context.getBeanDefinitionNames())
//                .map(name->context.getBeanDefinition(name))
////                .map(db->db.)
//        scanner.getSubTypesOf(Adapter.class)
//                .stream()
//                .filter(Class::isInterface)
    }

    @EventListener(ContextRefreshedEvent.class)
    public void registerAdapterBeans(){
    }


}
