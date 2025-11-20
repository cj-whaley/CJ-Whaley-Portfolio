
/** a "function object" that represents a method to decide
 *  whether a book meets a particular criterion */
interface IBookPredicate {
	boolean satisfiedBy(Book r);
}


class NonFictionPredicate implements IBookPredicate {
	public boolean satisfiedBy(Book r) {
		return r.isNonFiction();
	}	
}


class Under20Predicate implements IBookPredicate {
	public boolean satisfiedBy(Book r) {
		return r.salePrice() < 20;
	}	
}


class ByAuthorPredicate implements IBookPredicate {
	Author auth;

	public ByAuthorPredicate(Author auth) {
		this.auth = auth;
	}

	public boolean satisfiedBy(Book r) {
		return r.writtenBy(this.auth);
	}	
}

class anyFictionPredicate implements IBookPredicate {
	public boolean satisfiedBy(Book r) {
		return r.isFiction();
	}
}

/**
 * represents a predicate for checking if a book has a sale price > $50
 */
class anyOver50Predicate implements IBookPredicate {
	public boolean satisfiedBy(Book r) {
		return r.salePrice() > 50;
	}	
}

/** represents a predicate on books that has two sub-properties 
 * that need to be checked */
class CompoundBookPredicate implements IBookPredicate {
    IBookPredicate pred1;
    IBookPredicate pred2;
        
    CompoundBookPredicate(IBookPredicate pred1, IBookPredicate pred2) {
        super();
        this.pred1 = pred1;
        this.pred2 = pred2;
    }

    public boolean satisfiedBy(Book r) {
        return this.pred1.satisfiedBy(r) && this.pred2.satisfiedBy(r);
    }
}
