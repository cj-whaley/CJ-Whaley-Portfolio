/**
 * represents a phone tree for a soccer team
 * @author cjwhaley
 */
public class PhoneTree {
	IPT call1;
	IPT call2;
	Player p;
	
	PhoneTree(IPT call1, IPT call2, Player p) {
		super();
		this.call1 = call1;
		this.call2 = call2;
		this.p = p;
	}

}
