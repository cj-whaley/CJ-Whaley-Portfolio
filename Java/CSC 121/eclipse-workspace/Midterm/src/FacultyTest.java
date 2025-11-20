import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FacultyTest {

	IFaculty f1 = new Lecturer("Lee", "MAT", 5, 3);
	IFaculty f2 = new Professor("Bjorn", "HIS", 7, false, "Asia");
	IFaculty f3 = new Adjunct("Kwame", "BUS", 2, 55);

	@Test
	void test() {
		assertEquals( true, f1.canTeach(15) );
		assertEquals( true, f1.canTeach(24) );
		assertEquals( false, f1.canTeach(28) );
		assertEquals( false, f2.canTeach(16) );
		assertEquals( true, f2.canTeach(13) );
		assertEquals( true, f3.canTeach(6) );
		assertEquals( false, f3.canTeach(7) );
		assertEquals( false, f3.canTeach(-2) );
	}

}


interface IFaculty {
	/** determine if this faculty can teach given number of credits */
	public boolean canTeach(int credits);
}

abstract class AFaculty implements IFaculty{
	String name;
	String department;
	int yearsOfExperience;

	public AFaculty(String name, String department, int yearsOfExperience) {
		this.name = name;
		this.department = department;
		this.yearsOfExperience = yearsOfExperience;
	}

	/** determine if this faculty can teach given number of credits */
	public abstract boolean canTeach(int credits);
}


class Lecturer extends AFaculty {
	int contractLength;

	public Lecturer(String name, String department, int yearsOfExperience, int contractLength) {
		super(name, department, yearsOfExperience);
		this.contractLength = contractLength;
	}

	/** lecturers can teach up to 24 credits */
	public boolean canTeach(int credits) {
		if (credits > 0) {
			return credits <= 24;
		}
		else {
			return false;
		}
	}

}


class Professor extends AFaculty {
	boolean tenured;
	String researchArea;

	public Professor(String name, String department, int yearsOfExperience, boolean tenured, String researchArea) {
		super(name, department, yearsOfExperience);
		this.tenured = tenured;
		this.researchArea = researchArea;
	}

	/** tenured faculty can teach up to 18 credits; untenured can teach at most 15 */
	public boolean canTeach(int credits) {
		if (credits > 0) {
			if (tenured) {
				return credits <= 18;
			} else {
				return credits <= 15;
			}
		}
		else {
			return false;
		}
	}

}


class Adjunct extends AFaculty {
	int hourlyWage;

	public Adjunct(String name, String department, int yearsOfExperience, int hourlyWage) {
		super(name, department, yearsOfExperience);
		this.hourlyWage = hourlyWage;
	}

	/** adjuncts can teach 3 credits per year of experience, up to 12 hours */
	public boolean canTeach(int credits) {
		if (credits > 0) {
			return credits <= Math.min(12,  this.yearsOfExperience * 3);
		}
		else {
			return false;
		}
	}
}
