package org.usfirst.frc.team2832.smartdashboard.extensions;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.wpilibj.DriverStation;

public class AnimatedImage extends JComponent implements NumberBindable, ComponentListener, ChangeListener {

	private Image[] imgs = new Image[2];
	private BufferedImage img;
	private int curVal = 0;
	private Dimension d;
	private int imgWidth=1;
	private int imgHeight=1;
	
	public AnimatedImage(String path, int width, int height)
	{
		imgWidth = width;
		imgHeight = height;
		
		try {
			img=ImageIO.read(new File(path));
			//imgs[0]=ImageIO.read(new File("C:/Users/James/Desktop/image1.png"));
			//imgs[1]=ImageIO.read(new File("C:/Users/James/Desktop/image2.png"));
			d=new Dimension(imgWidth, imgHeight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void setBindableValue(double arg0) {
		if(curVal!=arg0 && arg0 * imgWidth < img.getWidth())
		{
			curVal = (int)arg0;
			repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(img != null)
			g.drawImage(img.getSubimage(imgWidth*curVal, 0, imgWidth, imgHeight), 0, 0, null);
		super.paintComponent(g);
	}
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return d;
	}
	@Override
	public Dimension getMaximumSize() {
		// TODO Auto-generated method stub
		return d;
	}

	@Override
	public Dimension getMinimumSize() {
		// TODO Auto-generated method stub
		return d;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void updatePath(String path)
	{
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateWidth(double width)
	{
		imgWidth = (int)width;
		d= new Dimension(imgWidth, imgHeight);
	}
	public void updateHeight(double height)
	{
		imgHeight = (int)height;
		d= new Dimension(imgWidth, imgHeight);
	}
}
