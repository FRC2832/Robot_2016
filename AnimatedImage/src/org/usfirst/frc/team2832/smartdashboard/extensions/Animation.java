package org.usfirst.frc.team2832.smartdashboard.extensions;

import java.awt.BorderLayout;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractValueWidget;
import edu.wpi.first.smartdashboard.properties.DoubleProperty;
import edu.wpi.first.smartdashboard.properties.FileProperty;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.smartdashboard.types.DataType;

public class Animation extends AbstractValueWidget{
	
	public final DoubleProperty imgWidth = new DoubleProperty(this, "Image Width", 32);
	public final DoubleProperty imgHeight = new DoubleProperty(this, "Image Height", 32);
	public final FileProperty path = new FileProperty(this, "Spritemap File","C:/Users/");
	
    public static final DataType[] TYPES = { DataType.NUMBER };
	
	public AnimatedImage ai;
	
	public Animation() {
		// TODO Auto-generated constructor stub
		super();
				
	}
	
	@Override
	public void propertyChanged(Property arg0) {
		if(arg0==path)
			ai.updatePath(path.getValue());
		if(arg0==imgWidth)
			ai.updateWidth(imgWidth.getValue());
		if(arg0==imgHeight)
			ai.updateHeight(imgHeight.getValue());
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		ai = new AnimatedImage("C:/Users/James/Desktop/image_test.png",32,32);
		setNumberBinding(ai);
		setValue(1);
		new BindableTableEntry(Robot.getTable(), getFieldName());
		
		add(ai,BorderLayout.CENTER);
		
		revalidate();
		repaint();
		
	}
	
}	
