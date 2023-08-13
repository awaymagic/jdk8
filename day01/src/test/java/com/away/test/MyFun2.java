package com.away.test;

/**
 * @author away
 * @date 2023/8/13
 */
@FunctionalInterface
public interface MyFun2<T, R> {

    R getValue(T t1, T t2);

}
