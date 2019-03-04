package grading;

public final class Missing 
{
	//Attributes for the class Missing
   private static double DEFAULT_MISSING_VALUE=0.0;
   
   public static double doubleValue(Double number)
   {
	   if(number != null)
	   {
		   return number.doubleValue();
	   }
	   return DEFAULT_MISSING_VALUE;
   }
   
   public static double doubleValue(Double number,double missingValue)
   {
	   if(number !=null)
	   {
		   return number.doubleValue();
	   }
	   return missingValue;
   }
}
