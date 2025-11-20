import java.util.Comparator;

public class SizeComparator implements Comparator<Tile>{

	@Override
	public int compare(Tile t1, Tile t2) {

		return t1.area() - t2.area();
	}

	

}

class NameComparator implements Comparator<Tile>{

	public int compare(Tile t1, Tile t2) {
		String name1 = t1.getName();
		String name2 = t2.getName();
		return name1.compareTo(name2);
	}

}
