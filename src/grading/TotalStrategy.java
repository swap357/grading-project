package grading;

import java.util.List;

public class TotalStrategy implements GradingStrategy {
	
	public TotalStrategy() {
		
	}

	@Override
	public Grade calculate(String Key, List<Grade> grades) throws SizeException {
		// TODO Auto-generated method stub
		Double grade_value = 0.0, total = 0.0;
		
		//Validate the list, it should not be null
		if(grades.isEmpty() || grades == null) 
		{		
			throw new SizeException();
		}
		else 
		{
			//Getting the values of Grade list
			for (Grade grade_itr : grades) 
			{
				grade_value = grade_itr.getValue();
				grade_value = Missing.doubleValue(grade_value);
				total = total + grade_value;				
			}
			
		}

		//Returning total of grades
		return new Grade(Key, total);
	}

}
