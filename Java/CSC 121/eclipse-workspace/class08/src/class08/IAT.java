package class08;

/**
 * represents an ancestral tree (family tree)
 */
public interface IAT {

	// produce the number of people in this tree with the given name
	public int countName(String checkName);

}


/**
 *  represents an unknown person in the tree 
 *   or a branch of the tree we know nothing about
 */
class Unknown implements IAT {

	// produce the number of people in this tree with the given name
	public int countName(String checkName) {
		return 0;
	}
	
	@Override
	public boolean equals(Object other) {
	    return other instanceof Unknown;
	}

	@Override
	public int hashCode() {
	    return Unknown.class.hashCode();
	}

	@Override
	public String toString() {
		return "Unknown []";
	}
	
	
}

/**
 * represents a person in the family tree 
 */
class Person implements IAT {
	String name;
	IAT mother;
	IAT father;
	
	Person(String name, IAT mother, IAT father) {
		super();
		this.name = name;
		this.mother = mother;
		this.father = father;
	}

	// produce the number of people in this tree with the given name
	public int countName(String checkName) {
		if (this.name.equals(checkName)) {
			return 1 + this.mother.countName(checkName) + this.father.countName(checkName);
		}
		else {
			return this.mother.countName(checkName) + this.father.countName(checkName);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((mother == null) ? 0 : mother.hashCode());
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (mother == null) {
			if (other.mother != null)
				return false;
		} else if (!mother.equals(other.mother))
			return false;
		if (father == null) {
			if (other.father != null)
				return false;
		} else if (!father.equals(other.father))
			return false;
		return true;
	}

	// OVERLOADING the constructor
	//  define more than one version of the same constructor/method 
	//  with different signatures/headers/parameters
	public Person(String name) {
		this.name = name;
		this.mother = new Unknown();
		this.father = new Unknown();
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", mother=" + mother + ", father=" + father + "]";
	}
	
	
}
