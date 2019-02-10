/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.commands.Teleop;
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
  final AHRS navX = new AHRS(Port.kMXP);

  public DriveTrain(){
    //inversion might not be working? figure that out later
    rearLeft.follow(frontLeft);
    centerLeft.follow(frontLeft);
    rearRight.follow(frontLeft);
    centerRight.follow(frontLeft);
    frontRight.follow(frontLeft);

    frontRight.setInverted(true);
    rearRight.setInverted(true);
   /* rearLeft.setInverted(InvertType.FollowMaster);
    centerLeft.setInverted(InvertType.FollowMaster);
    frontRight.setInverted(InvertType.OpposeMaster);
    rearRight.setInverted(InvertType.OpposeMaster);
    centerRight.setInverted(InvertType.OpposeMaster);*/

  }
  
  public void driveStraight(double speed) {
		frontLeft.set(ControlMode.PercentOutput, speed);
		rearLeft.set(ControlMode.PercentOutput, speed);
		frontRight.set(ControlMode.PercentOutput, speed);
    rearRight.set(ControlMode.PercentOutput, speed);
    centerRight.set(ControlMode.PercentOutput, speed);
    centerLeft.set(ControlMode.PercentOutput, speed);
  }
  
  public void tankDrive(double lSpeed, double rSpeed){
    frontLeft.set(ControlMode.PercentOutput, lSpeed);
    rearLeft.set(ControlMode.PercentOutput, lSpeed);
    centerLeft.set(ControlMode.PercentOutput, lSpeed);
		frontRight.set(ControlMode.PercentOutput, rSpeed);
    rearRight.set(ControlMode.PercentOutput, rSpeed);
    centerRight.set(ControlMode.PercentOutput, rSpeed);
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
    setDefaultCommand(new Teleop());
  }
}
