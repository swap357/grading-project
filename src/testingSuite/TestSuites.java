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
import grading.WeightedTotalStrategy;

class TestSuites {
	
	@Test
	public void weightedTotalStrategyCalculate()
	{
		// Create the weights and strategy for the course grade
		HashMap<String, Double> courseWeights = new HashMap<String, Double>();
		//courseWeights.put("PAs",     null);
		courseWeights.put("PAs",     0.4);
		courseWeights.put("HWs",     0.1);
		courseWeights.put("Midterm", 0.2);
		courseWeights.put("Final",   0.3);
		GradingStrategy courseStrategy = new WeightedTotalStrategy(courseWeights);
		
		//GradingStrategy courseStrategy = new WeightedTotalStrategy();
		
		// Put all of the grades in a List
		ArrayList<Grade> grades = new ArrayList<Grade>();
		//grades.add(new Grade("PAs", null));
		grades.add(new Grade("PAs", 93.0));
		grades.add(new Grade("HWs", 50.0));
		grades.add(new Grade("Midterm",80.0));
		grades.add(new Grade("Final",  75.0));
		
		System.out.println("grades: "+grades);
		// Calculate the final grade
		Grade courseGrade=null;
		try {
			courseGrade = courseStrategy.calculate("Course Grade", grades);
			System.out.println("coures grades: "+courseGrade.getValue());
		} catch (SizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double expectedGrade= 80.7;
		System.out.println("coures grades: "+courseGrade.getValue());
		assertEquals(expectedGrade, courseGrade.getValue(),"course grades");
	}

	@Test//(expected = SizeException.class)
	public void weightedTotalStrategyCalculate_NullGrade()
	{
		// Create the weights and strategy for the course grade
				HashMap<String, Double> courseWeights = new HashMap<String, Double>();
				courseWeights.put("PAs",     0.4);
				courseWeights.put("HWs",     0.1);
				courseWeights.put("Midterm", 0.2);
				courseWeights.put("Final",   0.3);
				GradingStrategy courseStrategy = new WeightedTotalStrategy(courseWeights);
				
				// Put all of the grades in a List
				ArrayList<Grade> grades = null;
				
				Assertions.assertThrows(SizeException.class, () -> {
					courseStrategy.calculate("Course Grade", grades);
				  });
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
		ArrayList<Grade> grades = new ArrayList<Grade>();
		grades.add(new Grade("PAs", 93.0));
		grades.add(new Grade("HWs", 50.0));
		grades.add(new Grade("Midterm",80.0));
		grades.add(new Grade("Final",  75.0));
		
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

}
