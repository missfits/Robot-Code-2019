/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HatchIntake extends Subsystem {
  final Compressor compressor = new Compressor(0);
	final DoubleSolenoid wingsSolenoid = new DoubleSolenoid(2, 3);
	final DoubleSolenoid armSolenoid = new DoubleSolenoid(0, 1);

  public void grabHatch(){
    wingsSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void releaseHatch(){
    wingsSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void armSolenoidIn(){
    armSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void armSolenoidOut(){
    armSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public Value getWingsPosition(){
    return wingsSolenoid.get();
  }

  public Value getArmsPosition(){
    return armSolenoid.get();
  }

  public void setUpCompressor(){
    compressor.setClosedLoopControl(true);
  }

  public String getPositionString(boolean checkingWings){
    //this code sucks ik
    //inner jacob is writhing in pain
    Value val = checkingWings ? getWingsPosition() : getArmsPosition();
    switch(val){
      case kForward:
        return "forward";
      case kReverse:
        return "reverse";
      case kOff:
        return "off";
      default:
        return "???";
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
