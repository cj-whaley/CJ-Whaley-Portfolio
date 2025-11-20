/**
 *  represents multiple types of digital media
 *  @author cjwhaley
 */
interface IMedia {

}

/*
 * represents a digital image
 */
class Image implements IMedia {
	String source;    // name of source file
	int size;         // in bytes
	int width;        // in pixels
	int height;       // in pixels
	String quality;   // low, medium, high, etc.
	
	Image(String source, int size, int width, int height, String quality) {
		super();
		this.source = source;
		this.size = size;
		this.width = width;
		this.height = height;
		this.quality = quality;
	}
	
	
}

/*
 * represents a piece of text
 */
class Text implements IMedia {
	String source;    // name of source file
	int size;         // in bytes
	int lines;        // number of lines necessary to visually represent text
	
	Text(String source, int size, int lines) {
		super();
		this.source = source;
		this.size = size;
		this.lines = lines;
	}
	
	
}

/*
 * represents a digital sound
 */
class Sound implements IMedia {
	String source;   // name of source file
	int size;        // in bytes
	int playTime;    // in seconds
	
	Sound(String source, int size, int playTime) {
		super();
		this.source = source;
		this.size = size;
		this.playTime = playTime;
	}
	
	
}