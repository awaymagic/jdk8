package com.away.test;

import org.junit.Test;

/**
 * @author away
 * @date 2023/8/13
 */
public class TestLambda {

    @Test
    public void test01(){

        String value = "hhxxttxs";

        String upperCase = toValue(value, String::toUpperCase);
        System.out.println("upperCase -> " + upperCase);

        String value1 = toValue(value, x -> x.substring(1,2));
        System.out.println(value1);

        String value3 = toValue(value, x -> x.substring(3,4));
        System.out.println(value3);

    }

    public String toValue(String param, MyFun fun) {
        return fun.getValue(param);
    }

    @Test
    public void test02(){
        Long handle = handle(10L, 20L, (x, y) -> x * y);
        System.out.println("handle -> " + handle);
    }

    public Long handle(Long l1, Long l2, MyFun2<Long, Long> function) {
        return function.getValue(l1, l2);
    }


}
