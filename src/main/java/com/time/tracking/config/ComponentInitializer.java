package com.timeTracking.config;

import com.timeTracking.config.annotation.InitializeComponent;
import com.timeTracking.servlet.RequestResolver;
import com.timeTracking.util.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentInitializer {

    private static ComponentInitializer initializer;

    private final Map<Class, Object> initializedObjects = new HashMap<>();
    private final RequestResolver requestResolver;

    public ComponentInitializer() {

        List<Class> annotatedClasses = Reflection.receiveAnnotatedClasses("com.timeTracking", InitializeComponent.class);
        annotatedClasses.forEach(aClass -> initializedObjects.put(aClass, createObject(aClass, annotatedClasses)));
        requestResolver = new RequestResolver(initializer);
    }

    private Object createObject(Class aClass, List<Class> annotatedClasses) {

        if (initializedObjects.containsKey(aClass)) {
            return initializedObjects.get(aClass);
        }

        Constructor constructor = aClass.getDeclaredConstructors()[0];
        Parameter[] parameters = constructor.getParameters();

        int paramLength = parameters.length;

        Object[] arguments = new Object[paramLength];
        for (int i = 0; i < paramLength; i++) {
            Class parameterType = parameters[i].getType();
            if (initializedObjects.containsKey(parameterType) || annotatedClasses.contains(parameterType)) {
                arguments[i] = createObject(parameterType, annotatedClasses);
            }
        }

        try {
            return constructor.newInstance(arguments);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return initializedObjects.get(aClass);
        }
    }


    public <T> T receiveCompomnemt(Class<T> classType) {
        return (T) initializedObjects.get(classType);
    }


    public static ComponentInitializer getInstance() {
        if (initializer == null) {
            synchronized (ComponentInitializer.class) {
                if (initializer == null) {
                    initializer = new ComponentInitializer();
                }
            }
        }

        return initializer;
    }

    public RequestResolver getRequestResolver() {
        return requestResolver;
    }

    public <T> List<T> receiveObjectsByType() {
        return null;
    }
}
