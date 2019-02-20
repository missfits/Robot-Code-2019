/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakeTilt;
import frc.robot.commands.PickUpBall;
import frc.robot.commands.PickUpHatch;
import frc.robot.commands.PlaceBall;
import frc.robot.commands.PlaceHatch;
import frc.robot.subsystems.Elevator.Height;
import frc.robot.commands.IntakeTilt.TiltPosition;
import frc.robot.commands.ApproachTarget;
import frc.robot.commands.Climb;
import frc.robot.commands.Elevate;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick xBox = new Joystick(0);
  Joystick leftStick = new Joystick(1);
  Joystick rightStick = new Joystick(2);
	
	public Button aButton = new JoystickButton(xBox,1);
	public Button bButton = new JoystickButton(xBox,2);
	public Button xButton = new JoystickButton(xBox,3);
	public Button yButton = new JoystickButton(xBox,4);
	public Button leftBumperButton = new JoystickButton(xBox,5);
	public Button rightBumperButton = new JoystickButton(xBox,6);
	public Button backButton = new JoystickButton(xBox,7);
  public Button startButton = new JoystickButton(xBox,8);

  public double leftStickY(){return -leftStick.getRawAxis(1);}
  public double rightStickY(){return -rightStick.getRawAxis(1);}

	public double xBoxLeftStickY() {return xBox.getRawAxis(1);}
	public double xBoxLeftStickX() {return xBox.getRawAxis(0);}
	public double xBoxRightStickY() {return xBox.getRawAxis(5);}
	public double xBoxRightStickX() {return xBox.getRawAxis(4);}
	public boolean leftTriggerPressed() {return xBox.getRawAxis(2) > 0.2;}
  public boolean rightTriggerPressed() {return xBox.getRawAxis(3) > 0.2;}
  
  public OI(){
		aButton.whenPressed(leftTriggerPressed()? new PlaceBall(Height.BOTTOM_BALL): new PlaceHatch(Height.BOTTOM_HATCH));
		bButton.whenPressed(leftTriggerPressed()? new PlaceBall(Height.MIDDLE_BALL): new PlaceHatch(Height.MIDDLE_HATCH));
		yButton.whenPressed(leftTriggerPressed()? new PlaceBall(Height.TOP_BALL): new PlaceHatch(Height.TOP_HATCH));
		
		leftBumperButton.whileHeld(new PickUpBall());
		rightBumperButton.whileHeld(new PickUpHatch());
		startButton.whenPressed(new Climb());
  }
}
