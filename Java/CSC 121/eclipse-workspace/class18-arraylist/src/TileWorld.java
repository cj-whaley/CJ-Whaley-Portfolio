import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/**
 * A GUI program that allows the user to click on rectangular tiles, overlaying on each other
 */
public class TileWorld {

    private ArrayList<Tile> tiles;  // first tile = bottom-most;  last tile = top-most
    
    /* for keeping track of dragging */
    private Tile dragging;   // if not null, it's the tile being dragged
    private int dragX;   // reference points for drag
    private int dragY;
    
    /* for keeping track of new drawing */
    private boolean isDrawingNew;
    private int newX;   // reference points for new one being drawn
    private int newY; 
    
    /* the sorting criteria */
    private Comparator<Tile> sortfunc = new SizeComparator();
    
    /* constructors */

    public TileWorld() {
        this(new Tile[] {});
    }
    
    TileWorld(Tile[] tiles) {
        this.tiles = new ArrayList<Tile>(Arrays.asList(tiles));
        this.dragging = null;
        this.isDrawingNew = false;
    }

    
    
    /* helper functions */
    
    
    /**
     * Returns the top-most tile that contains the given point.
     * Returns `null` if no tiles under the given point.
     */
    public Tile select(int x, int y) {
    	Tile selected = null;

    	for (int i = 0; i < tiles.size(); i = i + 1) {    	//ALTERNATE: for (int i = tiles.size() - 1;  i >= 0; i = i - 1) {
    		Tile cur = tiles.get(i);
    		if (cur.contains(x, y)) {
    			selected = cur;				    		    //ALTERNATE: return cur;
    		}
    	}
        
        return selected;
    }
    
    
    /**
     * Returns a list of the tiles that contain the given
     * point. (May be empty.) The order of tiles in the 
     * list that is returned preserves the order that the
     * tiles are in this world. 
     */
    public ArrayList<Tile> selectAll(int x, int y) {
        ArrayList<Tile> selected = new ArrayList<Tile>();
        
        for (int i = 0; i < tiles.size(); i = i + 1) {    	//ALTERNATE: for (int i = tiles.size() - 1;  i >= 0; i = i - 1) {
    		Tile cur = tiles.get(i);
    		if (cur.contains(x, y)) {
    			selected.add(cur);				    		    //ALTERNATE: return cur;
    		}
    	}
                
        return selected;
    }

    
    /**
     * Moves the top-most tile that contains the given 
     * point to the top (end) of the tiles list.
     * Does nothing if no tiles under the given point.
     */
    public void raise(int x, int y) {
    	int selectedIndex = -1;  // invalid
    	
    	for (int i = 0; i < tiles.size(); i = i + 1) {    	//ALTERNATE: for (int i = tiles.size() - 1;  i >= 0; i = i - 1) {
    		Tile cur = tiles.get(i);
    		if (cur.contains(x, y)) {
    			selectedIndex = i;			    		    //ALTERNATE: return cur;
    		}
    	}
    	
    	if (selectedIndex >= 0) {
    		Tile rem = this.tiles.remove(selectedIndex);
    		this.tiles.add(rem);
    	}
    	
    	
    	/*
        Tile selected = select(x, y);
        if (selected != null) {
        	this.tiles.remove(selected);
        	this.tiles.add(selected);
        }
        */
    }
    
    
    /**
     * Moves the top-most tile that contains the given 
     * point to the bottom (beginning) of the tiles list.
     * Does nothing if no tiles under the given point.
     */
    public void lower(int x, int y) {
         
        
    }
    
    
    /**
     * Removes the top-most tile that contains the given point.
     * Does nothing if no tiles under the given point.
     */    
    public void delete(int x, int y) {
        
        
    }
    
    
    /**
     * Removes all tiles that fall under the given point.
     */    
    public void deleteAll(int x, int y) {
        
    	
    	ArrayList<Tile> toRemove = new ArrayList<>();
    	
    	int N = tiles.size();
    	for (int i = 0; i < N; i = i + 1) {
    		Tile cur = tiles.get(i);
    		if (cur.contains(x, y)) {
    			toRemove.add(cur);
    		}
    	}
    	
    	
    	// go through toRemove and remove each of the
    	//  tiles from  this.tiles
    	this.tiles.removeAll(toRemove);
    	
    	/*
    	//   repeat ___X____ times
    	int N = tiles.size();
    	for (int i = 0; i < N; i = i + 1) {
    		Tile cur = tiles.get(i);
    		if (cur.contains(x, y)) {
    			tiles.remove(i);
    			i = i - 1;
    		}
    	
    	// repeat as long as ____X_____ (there are tiles under x,y)
    	//while ( this.select(x, y) != null ) {
    		//this.delete(x, y);
    	}
    	*/
        
    }

    
    public void rearrange(int spacing) {
    	
    	for (int i = 0; i < tiles.size(); i = i + 1) {
    		Tile cur = tiles.get(i);
    		cur.moveTo( 20*(i+1) , 20*(i+1) );
    	}
    }
    
    public void sortTiles() {
    	this.tiles.sort( sortfunc );
    	rearrange(20);
    }
    
    public void sortReverse() {
    	this.tiles.sort( sortfunc.reversed() );
    	rearrange(20);
    }
    
    

    /* GUI and event-handling methods below */
    
    public void update() {
        if (!tiles.isEmpty()) {
            tiles.get(tiles.size() - 1).update();
        }
    }

    public void draw(PApplet c) {
        c.background(255);
        for (Tile t : tiles) {
            t.draw(c);
        }
        
        if (this.isDrawingNew) {
            buildNew(true).draw(c);
        }
        
    }
    

    public void mousePressed(MouseEvent mev) {
        int x = mev.getX();
        int y = mev.getY();
        
        if (mev.getButton() == PApplet.LEFT) {
            this.dragX = x;
            this.dragY = y;
            this.dragging = select(x, y);
            
            if (mev.isShiftDown()) {
                this.lower(x, y);
            } else if (mev.isMetaDown()) {
                this.dragging = null;
            }else {
                this.raise(x, y);
            }
        } else if (mev.getButton() == PApplet.RIGHT) {
            this.dragging = null;
            
            if (mev.isShiftDown()) {
                this.deleteAll(x, y);
            } else {
                this.delete(x, y);
            }
        }
    }

    public void mouseReleased(MouseEvent mev) {
        if (this.isDrawingNew) {
            this.tiles.add(buildNew(false));
        }
        this.dragging = null;
        this.isDrawingNew = false;
    }

    public void mouseDragged(MouseEvent mev) {
        int x = mev.getX();
        int y = mev.getY();

        if (this.dragging == null) {
            if (!this.isDrawingNew) {
                this.isDrawingNew = true;
                this.newX = x;
                this.newY = y;
            }
        } else {
            this.dragging.moveBy(x - this.dragX, y - this.dragY);
        }
        
        this.dragX = x;
        this.dragY = y;
    }

    public void mouseEntered(MouseEvent mev) {
        // nothing
    }

    public void mouseMoved(MouseEvent mev) {
        // nothing
    }

    public void mouseExited(MouseEvent mev) {
        this.dragging = null;
    }

    public void keyPressed(KeyEvent kev) {
    	
    	
        //System.out.println(kev);
        if (kev.getKeyCode() == PApplet.DELETE || kev.getKeyCode() == PApplet.BACKSPACE) {
            if (!tiles.isEmpty()) {
                tiles.remove(tiles.size() - 1);
            }
        } else {
        	switch (Character.toLowerCase(kev.getKey())) {
        	case 'n': tiles.add(new Tile()); break;
        	case 'r': rearrange(20); break;
        	case '1': sortfunc = new SizeComparator();
        	          sortTiles(); break;
        	case '2': sortfunc = new NameComparator();
        		      sortTiles(); break;
        	case '>': 
        	case '.':
        		sortTiles(); break;
        	case '<': 
        	case ',': 
        		sortReverse(); break;
        	//  default: 
        	}
        	
        }
        	        	
        //	if (Character.toLowerCase(kev.getKey()) == 'n') {
        //    tiles.add(new Tile());
        //} else if (Character.toLowerCase(kev.getKey()) == 'r') {
        //    rearrange(20);
        //}
        
        // '1' -> sort by size
        // '2' -> sort by name
        // '>' -> sort increasing (smallest to largest)
        // '<' -> sort decreasing (largest to smallest)
        
    }
    

    /* private helper methods */
    
    /*
     * Using the (newX, newY) and (dragX, dragY) as the diagonal
     * endpoints, 
     * generates a gray, unlabeled tile if 'temporary';
     * otherwise, generates and returns a randomly-colored
     * and labeled tile.
     */
    private Tile buildNew(boolean temporary) {
        if (temporary) {
            return new Tile(Math.min(this.newX, this.dragX), 
                    Math.min(this.newY, this.dragY), 
                    Math.abs(this.newX - this.dragX),
                    Math.abs(this.newY - this.dragY), 
                    "", Color.GRAY, 0);
        } else {
            return new Tile(Math.min(this.newX, this.dragX), 
                    Math.min(this.newY, this.dragY), 
                    Math.abs(this.newX - this.dragX),
                    Math.abs(this.newY - this.dragY));
        }
    }

}
