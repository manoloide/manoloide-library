package manoloide.ImageProcessor;

import processing.core.*;

public class ImageProcessor implements PConstants{
	
	PApplet applet;
	public ImageProcessor(PApplet applet){
		this.applet = applet;
	}

	public PImage noise(float amount){
		return noise(applet.g, amount, false);
	}

	public PImage noise(PImage ori, float amount){
		return noise(ori, amount, false);
	}

	public PImage noise(float amount, boolean color){
		return noise(applet.g, amount, color);
	}

	public PImage noise(PImage ori, float amount, boolean color){
		applet.pushStyle();
		applet.colorMode(RGB);
		int w = ori.width;
		int h = ori.height;
		ori.loadPixels();
		for(int i = 0; i < ori.pixels.length; i++){
			int col;
			if(color){
				col = applet.color(applet.random(255),applet.random(255),applet.random(255));
			}else{
				col = applet.color(applet.random(255));
			}
			ori.pixels[i] = applet.lerpColor(ori.pixels[i], col, amount);
		}
		ori.updatePixels();
		applet.popStyle();
		return ori;
	}

	public void vignette(float inte){
		applet.image(vignette(applet.g.get(), inte), 0, 0);
	}

	public PImage vignette(PImage ori, float inte){
		applet.pushStyle();
		applet.colorMode(RGB);
		ori.loadPixels();
		float cx = ori.width/2;
		float cy = ori.height/2;
		float diag = applet.dist(0, 0, cx, cy);
		diag *= diag;
		for(int j = 0; j < ori.height; j++){
			for(int i = 0; i < ori.width; i++){
				float v = applet.pow(cx-i, 2)+applet.pow(cy-j, 2);
				int col = applet.lerpColor(ori.get(i, j), applet.color(0), applet.map(v, 0, diag, 0, inte)); 
				ori.set(i, j, col);
			}
		}
		ori.updatePixels();
		applet.popStyle();

		return ori;
	}
}
