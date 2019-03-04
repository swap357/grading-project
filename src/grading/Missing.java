package grading;

public class Missing 
{
   private double missingValue=0.0;
   
   public double doubleValue(Double number)
   {
	   if(number != null)
	   {
		   return number.doubleValue();
	   }
	   return missingValue;
   }
   
   public double doubleValue(Double number,double missingValue)
   {
	   if(number !=null)
	   {
		   return number.doubleValue();
	   }
	   return missingValue;
   }
}
