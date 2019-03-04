package grading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedTotalStrategy implements GradingStrategy {
	
	private Map<String, Double> weights;
	public WeightedTotalStrategy()
	{
		weights=null;
	}

	public WeightedTotalStrategy(Map<String, Double> courseWeights) {
		// TODO Auto-generated constructor stub
		this.weights = new HashMap<String, Double>();
		this.weights.putAll(weights);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Grade calculate(String Key, List<Grade> grades) throws SizeException {
		// TODO Auto-generated method stub
		
		Double weightedTotal = 0.0, tempWeight = 0.0, tempValue = 0.0;
		
		if(grades ==null || grades.isEmpty()) 
		{
			throw new SizeException();
		}
		else
		{
			for (Grade g : grades) 
			{
				tempValue=g.getValue();
				
				if(weights == null)
				{
					tempWeight = 1.0;
				}
				else
				{		
							
					//If the weight for a particular grade is unspecified, then it must be assigned to 1.0
					tempValue=Missing.doubleValue(tempValue);

					//Getting the weight for it's respective Grade object
					tempWeight = weights.get(g.getKey());
					
					//If the weight for a particular grade is null, then a weight of 1.0 must be assigned
					tempWeight = Missing.doubleValue(tempWeight, 1.0);
					
					//If the weight for a particular grade is negative, then a weight of 0.0 must be assigned
					if(tempWeight < 0.0)
						tempWeight = 0.0;
					
				}
				//Calculating the weighted total
				weightedTotal += tempWeight * tempValue;
			}
		}
		return new Grade(Key,weightedTotal);
	}

}
