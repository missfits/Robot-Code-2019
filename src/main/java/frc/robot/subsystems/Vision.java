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
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
 // double offsetValue =  NetworkTable.getTable("RaspberryPi").getNumber("Offset", 0);
  private NetworkTable table;
  private AnalogInput ultrasonic = new AnalogInput(0);
  
  /*public void convertDistance(int distanceInBits){
    int bitToMillimeters = distanceInBits * 5;
    System.out.println(bitToMillimeters);
  } */
  
  public double getDistance(){
    return (ultrasonic.getAverageValue() * 5) / 100.0;
  }
  public Vision(){
    table = NetworkTableInstance.getDefault().getTable("RaspberryPi");
    ultrasonic.setAverageBits(2);
  }
  public double getOffset(){
    return table.getEntry("Offset").getDouble(0);
  } 
  //double something = Vision.getOffset();
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
