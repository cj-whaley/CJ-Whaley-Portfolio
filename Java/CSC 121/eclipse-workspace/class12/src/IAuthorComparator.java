

public interface IAuthorComparator {
	 /* 
	  *  produces a negative number if Author a is "less than" author b according to some criteria
	  *  produces 0 if Author a is "equal to" author b according to some criteria
	  *  produces a positive number if Author a is "more than" author b according to some criteria
	  */
	
	int compare(Author a, Author b);

}

class YoBComparator implements IAuthorComparator {
	public int compare(Author a, Author b) {
		return a.yob - b.yob;
	}
}

class NameComparator implements IAuthorComparator {
	public int compare(Author a, Author b) {
		return a.name.length() - b.name.length();
	}
}
