
//interface IAuthorPredicate extends IPredicate <Author> {
//}

class BornAfter1940 implements IPredicate <Author> {

	public boolean satisfiedBy(Author a) {
		return a.yob > 1940;
	}
	
}


interface IPredicate <T> {
	boolean satisfiedBy(T a);
}