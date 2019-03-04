package grading;

import java.util.List;

public interface GradingStrategy {

	Grade calculate(String Key, List<Grade> grades) throws SizeException;

}
