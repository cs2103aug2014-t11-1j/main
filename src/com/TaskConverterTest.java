package com;

public class TaskConverterTest {
	public static void main(String[] args){
		TaskConverter taskConverter = TaskConverter.getInstance();
		Task testTask = new Task("test Task", "24/09/2014", "1200", "25/09/2014",  "1300", null);
		ModelTask task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		System.out.println(task.getEndDate().toString());
		System.out.println(task.getStartTime().toString());
		System.out.println(task.getEndTime().toString());
		
		testTask = new Task("test 2", "24/09/2014", "1200", null, "1300", null);
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		System.out.println(task.getStartTime().toString());
		System.out.println(task.getEndTime().toString());
		
		testTask = new Task("test 3", "24/09/2014", null, null, null, null);
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		
		testTask = new Task("test 4", null, null, null, null, null);
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		
		testTask = new Task("test 5", null, null, "24/09/2014", null, null);
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		
		testTask = new Task("test 6", null, null, null, null, "24/09/2014");
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		
		testTask = new Task("test 7", "24/09/2014", null, null, null, "25/09/2014");
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		System.out.println(task.getEndDate().toString());
		
		testTask = new Task("test 8", "24/09/2014", null, "25/09/2014", null, null);
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		System.out.println(task.getEndDate().toString());
		
		testTask = new Task("test 9", null, null, "24/09/2014", null, "25/09/2014");
		task = taskConverter.convert(testTask, 1);
		System.out.println(task.getEvent());
		System.out.println(task.getStartDate().toString());
		System.out.println(task.getEndDate().toString());
		
	}
}
