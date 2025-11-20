/**
 * @author cjwhaley
 */

/** represents media in an online gallery */
interface IMedia {
	/** computes how long it takes to download a file at some given 
	 *   network connection speed (in bytes per second)
	 */
	public int timeToDownload(int speed);
	
	/** determines whether the file is smaller than some given maximum size */
	public boolean smallerThan(int maxSize);
	
	/** determines whether the name of a file is the same as some given name (a String)*/
	public boolean sameName(String name);
}

/** represents an image in the online gallery */
class Image implements IMedia {
	String source;  // source file name
	int size;       // size of file in bytes
	int height;     // in pixels
	int width;      // in pixels
	String quality;  // low, medium, etc.

	Image(String source, int size, int height, int width, String quality) {
		this.source = source;
		this.size = size;
		this.height = height;
		this.width = width;
		this.quality = quality;
	}

	/** computes how long it takes to download a file at some given 
	 *   network connection speed (in bytes per second)
	 */
	public int timeToDownload(int speed) {
		return this.size / speed;
	}
	
	/** determines whether the file is smaller than some given maximum size */
	public boolean smallerThan(int maxSize) {
		return (this.size < maxSize);
	}
	
	/** determines whether the name of a file is the same as some given name (a String)*/
	public boolean sameName(String name) {
		return (this.source.equals(name));
	}
}

/** represents text content in the online gallery */
class Text implements IMedia {
	String source;  // source file name
	int size;       // size of file in bytes
	int lines;      // number of lines needed for visual representation

	Text(String source, int size, int lines) {
		this.source = source;
		this.size = size;
		this.lines = lines;
	}

	/** computes how long it takes to download a file at some given 
	 *   network connection speed (in bytes per second)
	 */
	public int timeToDownload(int speed) {
		return this.size / speed;
	}
	
	/** determines whether the file is smaller than some given maximum size */
	public boolean smallerThan(int maxSize) {
		return (this.size < maxSize);
	}
	
	/** determines whether the name of a file is the same as some given name (a String)*/
	public boolean sameName(String name) {
		return (this.source.equals(name));
	}
}

/** represents audio content in the online gallery */
class Sound implements IMedia {
	String source;  // source file name
	int size;       // size of file in bytes
	int time;       // playing time in seconds

	Sound(String source, int size, int time) {
		this.source = source;
		this.size = size;
		this.time = time;
	}

	/** computes how long it takes to download a file at some given 
	 *   network connection speed (in bytes per second)
	 */
	public int timeToDownload(int speed) {
		return this.size / speed;
	}
	
	/** determines whether the file is smaller than some given maximum size */
	public boolean smallerThan(int maxSize) {
		return (this.size < maxSize);
	}
	
	/** determines whether the name of a file is the same as some given name (a String)*/
	public boolean sameName(String name) {
		return (this.source.equals(name));
	}
}