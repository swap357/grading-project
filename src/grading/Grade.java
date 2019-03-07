package grading;

public class Grade implements Comparable<Grade>{

	private String key;
	private Double value;
	public Grade(String key) {
		this.key = key;
		this.value = 0.0;
	}
	
	public Grade(String key, Double v) {
		// TODO Auto-generated constructor stub
		this.key=key;
		this.value=v;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Grade [key=" + key + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Grade o) {
		// TODO Auto-generated method stub
		
		if(this.value == null && o.value != null)
    		return -1;
    	else if(this.value == null && o.value == null)
    		return 0;
    	else if(this.value != null && o.value == null)
    		return 1;
    	else
    		return this.value.compareTo(o.value);
		
	}

}
