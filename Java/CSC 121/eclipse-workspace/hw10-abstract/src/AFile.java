/**
 * represents various kinds of files
 * @author cjwhaley
 */
public abstract class AFile implements IFile{
	String name;
	String owner;

	AFile(String name, String owner) {
		this.name = name;
		this.owner = owner;
	}

	// Compute the size of this file in bytes
	public abstract int size();

	// determines how many seconds it takes to 
	//  download this file at a given download rate, in bytes per second.
	public int downloadTime(int rate) {
		return this.size()/rate;
	}

	// determines if a file can be compressed to reduce its size
	public boolean canCompress() {
		return true;
	}

}
