
//interface IBookPredicate {
//	boolean satisfiedBy(Book r);
//}


class NonFictionPredicate implements IPredicate <Book> {
	public boolean satisfiedBy(Book r) {
		return r.isNonFiction();
	}	
}

class Under20Predicate implements IPredicate <Book> {
	public boolean satisfiedBy(Book r) {
		return r.salePrice() < 20;
	}	
}

class ByAuthorPredicate implements IPredicate <Book> {
	Author auth;

	public ByAuthorPredicate(Author auth) {
		this.auth = auth;
	}

	public boolean satisfiedBy(Book r) {
		return r.writtenBy(this.auth);
	}	
}

