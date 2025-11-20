


interface IAuthorComparator {
	int compare(Author a, Author b);
}

class YoBCompare implements IAuthorComparator {
	public int compare(Author a, Author b) {
		return a.yob - b.yob;
	}
}

class NameLengthCompare implements IAuthorComparator {
	public int compare(Author a, Author b) {
		return a.name.length() - b.name.length();
	}
}
