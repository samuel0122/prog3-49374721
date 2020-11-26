package model.io.gif;

import java.io.File;
import java.io.IOException;

import com.gif4j.light.GifEncoder;
import com.gif4j.light.GifFrame;
import com.gif4j.light.GifImage;

import model.exceptions.io.BattleshipIOException;

/**
 * It generates and animated GIF
 * @author drizo
 */
public class AnimatedGIF {
	private GifImage gifImage; //GIF4J

	public AnimatedGIF() {
		gifImage = new GifImage();
		gifImage.setDefaultDelay(50);
		gifImage.setLoopNumber(0);
	}
	
	/**
	 * Add a new frame
	 * @param gif the frame to be added
	 * @throws BattleshipIOException if there is an internal error of the library
	 */
	public void addFrame(FrameGIF gif) throws BattleshipIOException {
        try {
			gifImage.addGifFrame(new GifFrame(gif.getBufferedImage()));
		} catch (InterruptedException e) {
			throw new BattleshipIOException("Error: problem adding frame");
		}
	}
	
	/**
	 * It saves the file
	 * @param file 
	 * @throws BattleshipIOException if there is an I/O exception
	 */
	public void saveFile(File file) throws BattleshipIOException {
	    try {
	        GifEncoder.encode(gifImage, file);
		} catch (IOException e) {
			throw new BattleshipIOException("Error: problem saving file");
		}
	}
}