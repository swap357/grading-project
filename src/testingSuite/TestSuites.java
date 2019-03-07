package testingSuite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.Gradient;
import grading.DropFilter;
import grading.Grade;
import grading.GradingStrategy;
import grading.Missing;
import grading.SizeException;
import grading.TotalStrategy;
import grading.WeightedTotalStrategy;

class TestSuites {
	
	
	HashMap<String, Double> courseWeights = new HashMap<String, Double>();
	ArrayList<Grade> grades = new ArrayList<Grade>();
			
	public TestSuites() {
		// TODO Auto-generated constructor stub
		// Create the weights and strategy for the course grade
		courseWeights.put("PAs",     0.4);
		courseWeights.put("HWs",     0.1);
		courseWeights.put("Midterm", 0.2);
		courseWeights.put("Final",   0.3);
		
		// Put all of the grades in a List
		grades.add(new Grade("PAs", 93.0));
		grades.add(new Grade("HWs", 50.0));
		grades.add(new Grade("Midterm",80.0));
		grades.add(new Grade("Final",  75.0));
	}
	
	@Test
	public void totalStrategyCalculate()
	{
			
		TotalStrategy courseStrategy = new TotalStrategy();
		ArrayList<Grade> hws = new ArrayList<Grade>();
		for (int i=0; i<5; i++)
		{
			hws.add(new Grade("HW"+(i+1), (double) (i+1*10)));
		}
		
		// Calculate the final grade
		Grade courseGrade=null;
		try {
			courseGrade = courseStrategy.calculate("HW Grade", hws);
			
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double expectedGrade= 60.0;
		assertEquals(expectedGrade, courseGrade.getValue(),"HW grades");
	}

	@Test//(expected = SizeException.class)
	public void totalStrategyCalculate_NullGrade()
	{
		TotalStrategy courseStrategy = new TotalStrategy();
		// Put all of the grades in a List
		ArrayList<Grade> hwGrade = null;
		Assertions.assertThrows(SizeException.class, () -> {
				courseStrategy.calculate("HW Grade", hwGrade);
			  });
	}

	@Test
	public void weightedTotalStrategyCalculate()
	{
			
		GradingStrategy courseStrategy = new WeightedTotalStrategy(courseWeights);
		// Calculate the final grade
		Grade courseGrade=null;
		try {
			courseGrade = courseStrategy.calculate("Course Grade", grades);
			
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double expectedGrade= 80.7;
		assertEquals(expectedGrade, courseGrade.getValue(),"course grades");
	}

	@Test//(expected = SizeException.class)
	public void weightedTotalStrategyCalculate_NullGrade()
	{
		GradingStrategy courseStrategy = new WeightedTotalStrategy(courseWeights);
		// Put all of the grades in a List
		ArrayList<Grade> grades = null;
		Assertions.assertThrows(SizeException.class, () -> {
				courseStrategy.calculate("Course Grade", grades);
			  });
	}
	
	@Test//(expected = SizeException.class)
	public void weightedTotalStrategyCalculate_NullWeights()
	{
		courseWeights.put("PAs", null);
		GradingStrategy courseStrategy = new WeightedTotalStrategy(courseWeights);
		
		//grades.add(new Grade("PAs", null));
		// Calculate the final grade
		Grade courseGrade=null;
		try {
			courseGrade = courseStrategy.calculate("Course Grade", grades);
			
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double expectedGrade= 136.5;
		assertEquals(expectedGrade, courseGrade.getValue(),"course grades");
		
		
	}
	
	@Test//(expected = SizeException.class)
	public void weightedTotalStrategyCalculate_CourseWeightNull()
	{
		
		GradingStrategy courseStrategy = new WeightedTotalStrategy();
		// Calculate the final grade
	    Grade courseGrade=null;
		try {
			courseGrade = courseStrategy.calculate("Course Grade", grades);
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Double expectedGrade2= 298.0;
		assertEquals(expectedGrade2, courseGrade.getValue(),"course grades");
	}
	
	@Test//(expected = SizeException.class)
	public void weightedTotalStrategyCalculate_NegativeWeights()
	{
		courseWeights.put("PAs", -1.0);
		GradingStrategy courseStrategy = new WeightedTotalStrategy(courseWeights);
		
		//grades.add(new Grade("PAs", null));
		// Calculate the final grade
		Grade courseGrade=null;
		try {
			courseGrade = courseStrategy.calculate("Course Grade", grades);
			
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double expectedGrade= 43.5;
		assertEquals(expectedGrade, courseGrade.getValue(),"course grades");
		
	}
	
	
	@Test
	public void missingDoubleTest()
	{
		double expectedNumber=0.0;
		Double number=null;
		// when expectedNumber is not null, it should return same number.
		assertEquals(expectedNumber,Missing.doubleValue(expectedNumber));
		// when number passed is null, we expect the value returned should be 0.0
		assertEquals(expectedNumber,Missing.doubleValue(number));
	}
	
	@Test
	public void missingArguDoubleTest()
	{
		double expectedNumber=1.0;
		Double number=null;
		// when number is null, the it should return the number passed in second argument.
		assertEquals(expectedNumber,Missing.doubleValue(number,expectedNumber));
		number = 10.0;
		//when the number is not null, it should return same number.
		assertEquals(number.doubleValue(),Missing.doubleValue(number,expectedNumber));
	}
	
	@Test
	public void dropFilterTest()
	{
		DropFilter d = new DropFilter(true, false);
				
		ArrayList<Grade> eGrades = new ArrayList<Grade>();
		eGrades.add(new Grade("PAs", 93.0));
		//eGrades.add(new Grade("HWs", 50.0));
		eGrades.add(new Grade("Midterm",80.0));
		eGrades.add(new Grade("Final",  75.0));
		Collections.sort(eGrades);
		
		try {
			assertEquals(eGrades, d.apply(grades),"drop filter working");
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void dropFilterTestException()
	{
		DropFilter d = new DropFilter(true, false);
				
		ArrayList<Grade> eGrades =null;
		
		Assertions.assertThrows(SizeException.class, () -> {
			d.apply(eGrades);
		  });
		
	}
}
