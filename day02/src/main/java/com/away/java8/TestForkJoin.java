package com.away.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {

	/**
	 * 注意和临界值的配置
	 */
	private static final Long count = 1000000000L;

	@Test
	public void test1(){
		long start = System.currentTimeMillis();
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinCalculate(0L, count);
		
		long sum = pool.invoke(task);
		System.out.println(sum);
		
		long end = System.currentTimeMillis();
		// 112-1953-1988-2654-2647-20663-113808
		System.out.println("耗费的时间为: " + (end - start));
	}
	
	@Test
	public void test2(){
		long start = System.currentTimeMillis();
		
		long sum = 0L;
		
		for (long i = 0L; i <= count; i++) {
			sum += i;
		}
		
		System.out.println(sum);
		
		long end = System.currentTimeMillis();
		// 34-3174-3132-4227-4223-31583
		System.out.println("耗费的时间为: " + (end - start));
	}
	
	@Test
	public void test3(){
		Instant start = Instant.now();

		Long sum = LongStream.rangeClosed(0L, count)
							// 并行流
							 .parallel()
							 .sum();
		
		System.out.println(sum);

		Instant end = Instant.now();

		System.out.println("耗费的时间为: " + Duration.between(start, end).toMillis());
	}

}
