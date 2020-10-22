package com.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalanced {

    private AtomicInteger atomicInteger = new AtomicInteger(0);



    public final int incrementAndGet(){


        int current =0;
        int next = 0;
        do {
            current = atomicInteger.get();
            next = (current>2138023820)?0:current+1;
        }while (!atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = incrementAndGet() % serviceInstances.size();
        ServiceInstance in = serviceInstances.get(index);
        return in;
    }
}
