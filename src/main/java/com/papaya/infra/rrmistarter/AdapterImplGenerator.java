package com.papaya.infra.rrmistarter;

import com.papaya.infra.common.Adapter;
import com.papaya.infra.rrmistarter.props.PropertiesHolder;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * @author Evgeny Borisov
 */
@Component
public class AdapterImplGenerator {

    @Autowired
    private GenericApplicationContext context;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PropertiesHolder propertiesHolder;

    @PostConstruct
    public void init() {
        System.out.println("adapters impl generation started");
        var scanner = new Reflections(propertiesHolder.getPackagesToScan());
        Set<Class<? extends Adapter>> set = scanner.getSubTypesOf(Adapter.class);
        for (Class<? extends Adapter> adapterIfc : set) {
            if (context.getBeansOfType(adapterIfc).isEmpty()) {
                Object proxyAdapter = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), adapterIfc.getInterfaces(), (proxy, method, args) -> {
                    if (args.length == 0) {

                        return restTemplate.getForObject("http://localhost:8081/api/generate/" + method.getName(), method.getReturnType());
                    } else {
                        return restTemplate.postForObject("http://localhost:8081/api/generate/" + method.getName(), args, method.getReturnType());
                    }
                });

                context.getDefaultListableBeanFactory().registerSingleton(adapterIfc.getName(),proxyAdapter);
            }
        }
//        Arrays.stream(context.getBeanDefinitionNames())
//                .map(name->context.getBeanDefinition(name))
////                .map(db->db.)
//        scanner.getSubTypesOf(Adapter.class)
//                .stream()
//                .filter(Class::isInterface)
    }

    @EventListener(ContextRefreshedEvent.class)
    public void registerAdapterBeans() {
    }


}
