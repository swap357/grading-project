package grading;

import java.util.List;
import java.util.Map;

public class WeightedTotalStrategy implements GradingStrategy {
	
	Map<String, Double> weights;
	public WeightedTotalStrategy()
	{
		
	}

	public WeightedTotalStrategy(Map<String, Double> courseWeights) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Grade calculate(String Key, List<Grade> grades) throws SizeException {
		// TODO Auto-generated method stub
		if(grades ==null || grades.isEmpty()) 
		{
			throw new SizeException();
		}
		
		return null;
	}

}
