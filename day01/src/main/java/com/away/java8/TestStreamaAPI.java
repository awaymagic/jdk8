package com.away.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 1	Stream 自己不会存储元素。
 * 2	Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * 3	Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 *
 * 一、Stream API 的操作步骤：
 * 1. 创建 Stream
 * 2. 中间操作
 * 3. 终止操作(终端操作)
 */
public class TestStreamaAPI {

	/**
	 * 1. 创建 Stream
	 */
	@Test
	public void test1(){

		// 1. Collection 提供了两个方法 stream() 与 parallelStream()
		List<String> list = new ArrayList<>();
		// 获取一个顺序流
		Stream<String> stream = list.stream();
		// 获取一个并行流
		Stream<String> parallelStream = list.parallelStream();
		
		// 2. 通过 Arrays 中的 stream() 获取一个数组流
		Integer[] nums = new Integer[10];
		Stream<Integer> stream1 = Arrays.stream(nums);
		
		// 3. 通过 Stream 类中静态方法 of()
		Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5, 6);
		
		// 4. 创建无限流
		// 4.1 迭代
		// 从谁开始什么逻辑
		Stream<Integer> stream3 = Stream.iterate(1, x -> x + 2).limit(10);
		stream3.forEach(System.out::println);
		
		// 4.2 生成
		Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
		stream4.forEach(System.out::println);
		
	}

	/**
	 * 2.中间操作
	 */
	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 59, 6666.66),
			new Employee(101, "张三", 18, 9999.99),
			new Employee(103, "王五", 28, 3333.33),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(104, "赵六", 8, 7777.77),
			new Employee(105, "田七", 38, 5555.55)
	);

	/**
	 * 筛选与切片
	 * 		filter —— 从流中排除某些元素。
	 * 		limit —— 截断流，使其元素不超过给定数量。
	 * 		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
	 * 		distinct —— 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */

	/**
	 * 内部迭代：迭代操作 Stream API 内部完成
	 */
	@Test
	public void test2(){
		// 所有的中间操作不会做任何的处理
		Stream<Employee> stream = emps.stream()
			.filter((e) -> {
				System.out.println("测试中间操作");
				return e.getAge() <= 35;
			});
		
		// 只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
		stream.forEach(System.out::println);
	}

	/**
	 * 外部迭代
	 */
	@Test
	public void test3(){
		Iterator<Employee> it = emps.iterator();

		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void test4(){
		emps.stream()
			.filter(e -> {
				System.out.println("短路！"); // &&  ||
				return e.getSalary() >= 5000;
			}).limit(3)
			.forEach(System.out::println);
	}
	
	@Test
	public void test5(){
		emps.parallelStream()
			.filter(e -> e.getSalary() >= 5000)
			.skip(2)
			.forEach(System.out::println);
	}
	
	@Test
	public void test6(){
		emps.stream()
				// 去重
				.distinct()
				.forEach(System.out::println);
	}

}
