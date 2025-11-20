/**
 *  represents a computer image file
 */
	class Image {
	    int height;
	    int width;
	    String source;
	    String quality;
	    
	    Image(int width, int height, String source, String quality) {
	        this.height = height;
	        this.width = width;
	        this.source = source;
	        this.quality = quality;
	    }
	    
	    /* TEMPLATE:
		  public ??? ImageMethod(...) {
		    ... this.height ...          -- int
		    ... this.width ...           -- int
		    ... this.source ...          -- String
		    ... this.quality ...         -- String
		  }
		  */ 
	}
/*
 *  height and width were both defined as ints, so I changed them from doubles to ints in the constructor because images
 *     are often measured in pixels, which are whole numbers
 *     
 *  source was left out of the constructor
 * 
 *  changed documentation comment to a JavaDoc comment
 */
