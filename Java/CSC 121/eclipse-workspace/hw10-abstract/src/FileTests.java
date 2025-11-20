import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileTests {

    IFile text = new TextFile("Files.java", "nah", 2048);
    IFile img = new ImageFile("photo.jpg", "tapia", 640, 480);
    IFile aud = new AudioFile("watcha_want.mp3", "mcdowell", 44, 120000);
    IFile text2 = new TextFile("Hello.java", "bridges", 92);
    IFile img2 = new ImageFile("dog.jpg", "pulaski", 8, 10);
    IFile aud2 = new AudioFile("song.mp3", "knapp", 2, 18);

    @Test
    // Test the size method
    public void testSize() {
        assertEquals(2048, this.text.size());
        assertEquals(307200, this.img.size());
        assertEquals(5280000, this.aud.size());
    }
    
    @Test
    // Test the downloadTime method
    public void testDownloadTime() {
        assertEquals(512, this.text.downloadTime(4));
        assertEquals(1024, this.img.downloadTime(300));
        assertEquals(7040, this.aud.downloadTime(750));
        assertEquals(46, this.text2.downloadTime(2));
        assertEquals(2, this.img2.downloadTime(40));
        assertEquals(4, this.aud2.downloadTime(9));
    }
    
    @Test
    // Test the canCompress method
    public void testCanCompress() {
        assertEquals(true, this.text.canCompress());
        assertEquals(true, this.img.canCompress());
        assertEquals(true, this.aud.canCompress());
        assertEquals(false, this.text2.canCompress());
        assertEquals(true, this.img2.canCompress());
        assertEquals(true, this.aud2.canCompress());
    }
}