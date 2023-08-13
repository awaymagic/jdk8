package com.away.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestStreamAPI3 {
	
	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 79, 6666.66, Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);
	
	//3. 终止操作
	/**
	 * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
	 * 	 归约 —— 可以将流中元素反复结合起来，得到一个值。
	 */
	@Test
	public void test1(){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		Integer sum = list.stream()
				// 	起始值0作为y，从流中取的每一个元素作为y，依次计算
				.reduce(0, (x, y) -> x + y);
		
		System.out.println(sum);
		
		System.out.println("----------------------------------------");
		
		Optional<Double> op = emps.stream()
			.map(Employee::getSalary)
			.reduce(Double::sum);
		
		System.out.println(op.get());
	}

	/**
	 * 需求：搜索名字中 “六” 出现的次数
	 */
	@Test
	public void test2(){
		Optional<Integer> sum = emps.stream()
			.map(Employee::getName)
			.flatMap(TestStreamAPI1::filterCharacter)
			.map(ch -> {
				if(ch.equals('六')) {
					return 1;
				} else {
					return 0;
				}
			}).reduce(Integer::sum);
		
		System.out.println(sum.get());
	}

	/**
	 * collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
	 */
	@Test
	public void test3(){
		List<String> list = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toList());
		
		list.forEach(System.out::println);
		
		System.out.println("----------------------------------");
		
		Set<String> set = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toSet());
		
		set.forEach(System.out::println);

		System.out.println("----------------------------------");
		
		HashSet<String> hs = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.toCollection(HashSet::new));
		
		hs.forEach(System.out::println);
	}
	
	@Test
	public void test4(){
		// 最大工资
		Optional<Double> max = emps.stream()
			.map(Employee::getSalary)
			.collect(Collectors.maxBy(Double::compare));
		Optional<Employee> max1 = emps.stream().max(Comparator.comparingDouble(Employee::getSalary));

		System.out.println(max.get());

		// 最低工资
		Optional<Employee> op = emps.stream()
			.collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
		Optional<Employee> optional = emps.stream().min(Comparator.comparingDouble(Employee::getSalary));
		Optional<Double> optional1 = emps.stream()
				.map(Employee::getSalary).min(Double::compare);

		System.out.println(op.get());

		// 工资总和
		Double sum = emps.stream()
			.collect(Collectors.summingDouble(Employee::getSalary));
		Double sum1 = emps.stream().mapToDouble(Employee::getSalary).sum();

		System.out.println(sum);

		// 工资平均值
		Double avg = emps.stream()
			.collect(Collectors.averagingDouble(Employee::getSalary));
		
		System.out.println(avg);

		// 总人数
		Long count = emps.stream()
			.collect(Collectors.counting());

		System.out.println(count);
		
		System.out.println("--------------------------------------------");


		DoubleSummaryStatistics dss = emps.stream()
			.collect(Collectors.summarizingDouble(Employee::getSalary));
		// 所有工资求和
		System.out.println(dss.getMax());
		System.out.println(dss.getMin());
		System.out.println(dss.getAverage());
		System.out.println(dss.getSum());
		System.out.println(dss.getCount());

	}

	/**
	 * 分组
	 */
	@Test
	public void test5(){
		Map<Status, List<Employee>> map = emps.stream()
			.collect(Collectors.groupingBy(Employee::getStatus));
		
		System.out.println(map);

	}

	/**
	 * 多级分组
	 */
	@Test
	public void test6(){
		Map<Status, Map<String, List<Employee>>> map = emps.stream()
			.collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
				if(e.getAge() >= 60) {
					return "老年";
				} else if(e.getAge() >= 35) {
					return "中年";
				} else {
					return "成年";
				}
			})));
		
		System.out.println(map);
	}

	/**
	 * 分区
	 */
	@Test
	public void test7(){
		Map<Boolean, List<Employee>> map = emps.stream()
			.collect(Collectors.partitioningBy(e -> e.getSalary() >= 5000));
		
		System.out.println(map);
	}
	
	@Test
	public void test8(){
		// 拼接
		String str = emps.stream()
			.map(Employee::getName)
			.collect(Collectors.joining("," , "----", "----"));
		
		System.out.println(str);
	}
	
	@Test
	public void test9(){
		Optional<Double> sum = emps.stream()
			.map(Employee::getSalary)
			.collect(Collectors.reducing(Double::sum));
		
		System.out.println(sum.get());
	}

}
