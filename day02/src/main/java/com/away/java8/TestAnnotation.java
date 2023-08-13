package com.away.java8;

import com.sun.istack.internal.NotNull;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 *
 * @author away
 * @date 2023/8/13
 */
public class TestAnnotation {

    // 类型注解
    // private @NotNull Object object = null;

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method show = clazz.getMethod("show");

        MyAnnotation[] myAnnotations = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : myAnnotations) {
            // System.out.println(myAnnotation);
            System.out.println(myAnnotation.value());
        }
    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("abc") String str) {

    }

}
