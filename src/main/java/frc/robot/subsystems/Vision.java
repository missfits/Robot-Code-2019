/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  public enum TargetSpot{
    SIDE, CENTER
  }
 // double offsetValue =  NetworkTable.getTable("RaspberryPi").getNumber("Offset", 0);
  private NetworkTable table;
  private AnalogInput ultrasonic = new AnalogInput(0);
  private Relay light = new Relay(0);

  
  /*public void convertDistance(int distanceInBits){
    int bitToMillimeters = distanceInBits * 5;
    System.out.println(bitToMillimeters);
  } */
  
  public double getDistance(){
    //units is in inches (idk why it's supposed to be in mm)
    return (ultrasonic.getAverageValue() * 5) / 100.0;
  }
  public Vision(){
    table = NetworkTableInstance.getDefault().getTable("RaspberryPi");
    ultrasonic.setAverageBits(2);
  }
  public void setVisionMode(boolean b){
    table.getEntry("Vision Mode").setBoolean(b);
 
    if (b){
      light.set(Value.kReverse);
    }else{
      light.set(Value.kOff);
    }
  }
  public double getOffset(TargetSpot target){
    return table.getEntry(target == TargetSpot.CENTER? "Center Offset" : "Side Offset").getDouble(0);
  } 
  //double something = Vision.getOffset();
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public int getContourNumber(){
    return (int)table.getEntry("Contour Number").getDouble(0);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
