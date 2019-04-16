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
import frc.robot.Robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
  private Relay light1 = new Relay(0);
  private DoubleSolenoid light2 = new DoubleSolenoid(4,5);
  
  public double getDistance(){
    //units is in inches (idk why it's supposed to be in mm)
    return (ultrasonic.getAverageValue() * 5) / 100.0;
  }
  public Vision(){
    table = NetworkTableInstance.getDefault().getTable("RaspberryPi");
    ultrasonic.setAverageBits(2);
  }
  public void setVisionMode(boolean b){
    System.out.println("Setting Vision Mode " + b);
    table.getEntry("Vision Mode").setBoolean(b);
 
    if(table.getEntry("Reverse Drive").getBoolean(false)){
      light2.set(b ? edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward : edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse);
    }else{
      light1.set(b ? Value.kReverse : Value.kOff);
    }
  }

  public void useBackCam(boolean b){
    table.getEntry("Reverse Drive").setBoolean(b);
  }

  public boolean getRobotFront(){
    return table.getEntry("Reverse Drive").getBoolean(false);
  }

  public double getOffset(TargetSpot target){
    return table.getEntry(target == TargetSpot.CENTER? "Center Offset" : "Side Offset").getDouble(0);
  } 
  public boolean getVisionMode(){
    //System.out.println("Getting vision mode " + table.getEntry("Vision Mode").getBoolean(false));
    return table.getEntry("Vision Mode").getBoolean(false);
  }
 
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