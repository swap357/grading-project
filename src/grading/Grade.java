package grading;

public class Grade {

	private String key;
	private Double value;
	public Grade(String key) {
		
	}
	public Grade(String key, Double v) {
		// TODO Auto-generated constructor stub
		this.key=key;
		this.value=v;
	}
	public Grade(String key, double v) {
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

}
