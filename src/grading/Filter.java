package grading;

import java.util.List;

public interface Filter {

	List<Grade> apply(List<Grade> pas) throws SizeException;

}
