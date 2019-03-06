package grading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DropFilter implements Filter {
	
	private boolean b;
	private boolean c;
	
public DropFilter() {
		
		b = c = true;

	}
	
	public DropFilter(boolean b, boolean c) {
		// TODO Auto-generated constructor stub
		
		this.b = b;
		this.c = c;
		
	}

	@Override
	public List<Grade> apply(List<Grade> pas) throws SizeException{
		// TODO Auto-generated method stub
		//Integer variable to determine the number of dropped elements
				int drop = 0;
				List<Grade> tempGradelist = new ArrayList<Grade>();
			
				if(this.c == true)	
					drop++;
				if(this.b == true)
					drop++;
				
				//Will throw exception if list is null or empty
				if(pas == null)
					throw new SizeException();
				else if(pas.size() <= drop)
					throw new SizeException();
				else {
					
					Grade tempGrade;
					Iterator<Grade> itr = pas.iterator();
					
					while(itr.hasNext()) {
						
						tempGrade = (Grade)itr.next();
						Grade grade = new Grade(tempGrade.getKey(), tempGrade.getValue());
						tempGradelist.add(grade);
						
					}
					
					//Sorting the list w.r.t the values associated to the grades
					Collections.sort(tempGradelist);
		        
					//If two elements are to be dropped
					if(drop == 2) {
						
						tempGradelist.remove(0);
						tempGradelist.remove(tempGradelist.size()-1);
						
					}
					else if(drop == 1){
						
						int dropIndex = (b == true ? 0 : tempGradelist.size()-1);
						tempGradelist.remove(dropIndex);
						
					}
					
				}
				
				//Return the new list with the dropped elements
				return tempGradelist;
	}

	
}
