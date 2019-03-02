package grading;

import java.util.List;

public interface Filter {

	Object apply(List<Grade> pas);

}
