/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 
  //2019 bot
 /*public static int frontLeft = 2;
  public static int rearLeft = 1;
  public static int frontRight = 6;
  public static int rearRight = 3;
  public static int centerLeft = 5;
  public static int centerRight = 4;*/

  
  //2018 bot
 public static int frontLeft = 2;
  public static int rearLeft = 3;
  public static int frontRight = 1;
  public static int rearRight = 4;
  public static int centerLeft = 6;
  public static int centerRight = 7;
  

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
