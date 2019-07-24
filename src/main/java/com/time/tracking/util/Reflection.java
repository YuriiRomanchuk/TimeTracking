package com.time.tracking.util;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Reflection {

    public static List<Class> receiveAnnotatedClasses(String packageName, Class<? extends Annotation> annotationType) {

        List<Class> classes = getClasses(packageName);

        return classes.stream()
                .filter(c -> Arrays.asList(c.getAnnotations()).stream().anyMatch(annotation -> annotation.annotationType().equals(annotationType)))
                .collect(Collectors.toList());
    }

    private static List<Class> getClasses(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            List<Class> classes = new ArrayList<>();
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
            return classes;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static <A extends Annotation> Map<Method, A> receiveAnnotatedMethods(Class type, Class<A> annotationType) {

        Map<Method, A> methodsMap = new HashMap<>();

        for (Method declaredMethod : type.getDeclaredMethods()) {
            methodsMap.put(declaredMethod, ((A) Arrays.stream(declaredMethod.getDeclaredAnnotations()).filter(annotation -> annotationType.isAssignableFrom(annotation.annotationType())).findFirst().orElse(null)));
        }

        return methodsMap.entrySet()
                .stream()
                .filter(e -> e.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}

