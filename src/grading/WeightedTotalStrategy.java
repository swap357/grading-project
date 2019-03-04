package grading;

import java.util.List;
import java.util.Map;

public class WeightedTotalStrategy implements GradingStrategy {
	
	public Map<String, Double> weights;
	public WeightedTotalStrategy()
	{
		weights=null;
	}

	public WeightedTotalStrategy(Map<String, Double> courseWeights) {
		// TODO Auto-generated constructor stub
		weights=courseWeights;
	}

	@Override
	public Grade calculate(String Key, List<Grade> grades) throws SizeException {
		// TODO Auto-generated method stub
		Missing missing=new Missing();
		Grade grade=null;
		if(grades ==null || grades.isEmpty()) 
		{
			throw new SizeException();
		}
		else if(weights == null)
		{
			grade =new Grade(Key,1.0);
		}
		
		for (Grade g : grades) 
		{
			
			if(weights.containsKey(g))
			{		
				Double weight=weights.get(Key);
				if(weight==null)
				{
					
					grade =  new Grade(Key,missing.doubleValue(weight, 1.0));
				}
				else if(weight <= 0.0)
				{
					grade = new Grade(Key,missing.doubleValue(null));
				}
				else if(g.getValue()==null)
				{
					grade = new Grade(Key,missing.doubleValue(g.getValue()));
				}
				
			}
			
		}
		
		return grade;
	}

}
