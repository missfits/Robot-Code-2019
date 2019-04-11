/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.commands.TeleopDriveTrain;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  final TalonSRX frontLeft = new TalonSRX(RobotMap.frontLeft);
	final TalonSRX rearLeft = new TalonSRX(RobotMap.rearLeft);
	final TalonSRX frontRight = new TalonSRX(RobotMap.frontRight);
  final TalonSRX rearRight = new TalonSRX(RobotMap.rearRight);
  final TalonSRX centerLeft = new TalonSRX(RobotMap.centerLeft);
  final TalonSRX centerRight = new TalonSRX(RobotMap.centerRight);
  final AHRS navX = new AHRS(SPI.Port.kMXP);
  private boolean reverseMode = false;

  public DriveTrain(){
    //inversion might not be working? figure that out later
    rearLeft.follow(frontLeft);
    centerLeft.follow(frontLeft);
    rearRight.follow(frontLeft);
    centerRight.follow(frontLeft);
    frontRight.follow(frontLeft);

    frontRight.setInverted(true);
    centerRight.setInverted(true);
    rearRight.setInverted(true);
   /* rearLeft.setInverted(InvertType.FollowMaster);
    centerLeft.setInverted(InvertType.FollowMaster);
    frontRight.setInverted(InvertType.OpposeMaster);
    rearRight.setInverted(InvertType.OpposeMaster);
    centerRight.setInverted(InvertType.OpposeMaster);*/

  }

  public boolean getReverseMode(){
    return reverseMode;
  }

  public void setReverseMode(boolean b){
    reverseMode = b;
  }
  
  public void driveStraight(double speed) {
    int directionMultiplier = reverseMode ? -1 : 1;
		frontLeft.set(ControlMode.PercentOutput,directionMultiplier * speed);
		rearLeft.set(ControlMode.PercentOutput, directionMultiplier * speed);
		frontRight.set(ControlMode.PercentOutput, directionMultiplier * speed);
    rearRight.set(ControlMode.PercentOutput, directionMultiplier * speed);
    centerRight.set(ControlMode.PercentOutput, directionMultiplier * speed);
    centerLeft.set(ControlMode.PercentOutput, directionMultiplier * speed);
  }
  
  public void tankDrive(double lSpeed, double rSpeed){
    double actualLSpeed = lSpeed;
    double actualRSpeed = rSpeed;
    if(reverseMode){
      actualLSpeed = -rSpeed;
      actualRSpeed = -lSpeed;
    }
    frontLeft.set(ControlMode.PercentOutput, actualLSpeed);
    rearLeft.set(ControlMode.PercentOutput, actualLSpeed);
    centerLeft.set(ControlMode.PercentOutput, actualLSpeed);
		frontRight.set(ControlMode.PercentOutput, actualRSpeed);
    rearRight.set(ControlMode.PercentOutput, actualRSpeed);
    centerRight.set(ControlMode.PercentOutput, actualRSpeed);
    SmartDashboard.putNumber("L: ", actualLSpeed);
    SmartDashboard.putNumber("R: ",  actualRSpeed);
  }

  public double getLeftPosition() {
    return centerLeft.getSelectedSensorPosition()*6*Math.PI/4096;
  }
  public double getRightPosition() {
    return centerRight.getSelectedSensorPosition()*6*Math.PI/4096;
  }



  
  public double getAngle() {
    return navX.getAngle();


  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDriveTrain());
  }
}
