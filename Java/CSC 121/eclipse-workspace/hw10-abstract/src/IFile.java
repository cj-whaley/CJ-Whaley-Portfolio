
// Represents various kinds of files
interface IFile{
	// Compute the size of this file in bytes
	public int size();

	// determines how many seconds it takes to 
	//  download this file at a given download rate, in bytes per second.
	public int downloadTime(int rate);

	// determines if a file can be compressed to reduce its size
	public boolean canCompress();
}

// Represents a text file
class TextFile extends AFile{
	int length;   // in bytes

	TextFile(String name, String owner, int length){
		super(name, owner);
		this.length = length;
	}

	// Compute the size of this TextFile, in bytes
	public int size(){
		return this.length;
	}  

	// determines if this TextFile can be compressed to reduce its size
	public boolean canCompress() {
		if (this.size() >= 100) {
			return true;
		}
		else {
			return false;
		}
	}
}

//to represent an image file
class ImageFile extends AFile{
	int width;   // in pixels
	int height;  // in pixels

	ImageFile(String name, String owner, int width, int height){
		super(name, owner);
		this.width = width;
		this.height = height;
	}

	// Compute the size of this ImageFile, in bytes
	public int size(){
		return this.width * this.height;
	}  
}

//to represent an audio file
class AudioFile extends AFile{
	int speed;   // in bytes per second
	int length;  // in seconds of recording time

	AudioFile(String name, String owner, int speed, int length){
		super(name, owner);
		this.speed = speed;
		this.length = length;
	}

	// compute the size of this AudioFile
	public int size(){
		return this.speed * this.length;
	}  
}
