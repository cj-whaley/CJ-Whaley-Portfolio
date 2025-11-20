import java.util.Objects;

public class Person implements IPerson {
	String name;
	IPerson mom;
	IPerson dad;
	IPerson child;
	
	Person(String name, IPerson mom, IPerson dad, IPerson child) {
		this.name = name;
		this.mom = mom;
		this.dad = dad;
		this.child = child;
	}
	
	Person(String name) {
		this.name = name;
		this.mom = new Unknown();
		this.dad = new Unknown();
	}


	Person(String name, IPerson mom, IPerson dad){
		this.name = name;
		this.mom = mom;
		this.dad = dad;
		this.mom.updateChild(this);
		this.dad.updateChild(this);

	}
	
	public void updateChild(IPerson child) {
		this.child = child;
	}

	@Override
	public int hashCode() {
		return Objects.hash(child, dad, mom, name);
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
		return //Objects.equals(child, other.child) &&
				Objects.equals(dad, other.dad) && Objects.equals(mom, other.mom)
				&& Objects.equals(name, other.name);
	}
	

}
