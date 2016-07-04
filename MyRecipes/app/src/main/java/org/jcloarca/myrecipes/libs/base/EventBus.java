package org.jcloarca.myrecipes.libs.base;

/**
 * Created by JCLoarca on 7/3/2016 10:17 PM.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
