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
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.*;
import frc.robot.commands.IntakeTilt.TiltPosition;
import frc.robot.commands.MoveClimber.ClimbDirection;
import frc.robot.commands.RunIntakeArm.ArmDirection;
import frc.robot.commands.RunIntakeWheels.WheelDirection;
import frc.robot.subsystems.Elevator.Height;


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
	public Button xBoxLeftTrigger = new xBoxTrigger(xBox, 2);
	public Button xBoxRightTrigger = new xBoxTrigger(xBox, 3);

	public Button rightStickTrigger = new JoystickButton(rightStick,1);
	public Button rightStickThumbButton = new JoystickButton(rightStick,2);
	public Button rightStick4 = new JoystickButton(rightStick,4);
	public Button rightStick5 = new JoystickButton(rightStick,5);

	public Button leftStickTrigger = new JoystickButton(leftStick, 1);
	public Button leftStick12 = new JoystickButton(leftStick, 12);

  public double leftStickY(){return -leftStick.getRawAxis(1);}
  public double rightStickY(){return -rightStick.getRawAxis(1);}

	public double xBoxLeftStickY() {return xBox.getRawAxis(1);}
	public double xBoxLeftStickX() {return xBox.getRawAxis(0);}
	public double xBoxRightStickY() {return xBox.getRawAxis(5);}
	public double xBoxRightStickX() {return xBox.getRawAxis(4);}
	//public boolean reverseMode(){return leftStickTrigger.get();}
  
  public OI(){
		//aButton.whenPressed(leftTrigger.get()? new PlaceBall(Height.BOTTOM_BALL): new PlaceHatch(Height.BOTTOM_HATCH));
		//bButton.whenPressed(leftTrigger.get()? new PlaceBall(Height.MIDDLE_BALL): new PlaceHatch(Height.MIDDLE_HATCH));
		//yButton.whenPressed(leftTrigger.get()? new PlaceBall(Height.TOP_BALL): new PlaceHatch(Height.TOP_HATCH));
		/*aButton.whileHeld(new IntakeTilt(TiltPosition.BALL_PICKUP));
		yButton.whileHeld(new IntakeTilt(TiltPosition.HATCH_PICKUP));
		bButton.whileHeld(new IntakeTilt(TiltPosition.BALL_SHOOT_POSITION));*/
		aButton.whileHeld(new Elevate(Height.BALL_PICKUP));
		//xButton.whileHeld(new Elevate(Height.HOLDING_BALL));
		yButton.whileHeld(new Elevate(Height.CARGO_SHIP));
		bButton.whileHeld(new Elevate(Height.BOTTOM_ROCKET));
		//xButton.whileHeld(new IntakeTilt(TiltPosition.CLIMBING));
		//xButton.whileHeld(new Climb());
		//startButton.whileHeld(new Climb());
		backButton.whenPressed(new MoveWings());
		startButton.whenPressed(new MoveHatchArm());
		xButton.whileHeld(new RunHatchIntake());

		xBoxLeftTrigger.whileHeld(new RunIntakeWheels(WheelDirection.IN));
		xBoxRightTrigger.whileHeld(new RunIntakeWheels(WheelDirection.OUT));
		leftBumperButton.whileHeld(new RunIntakeArm(ArmDirection.UP));
		rightBumperButton.whileHeld(new RunIntakeArm(ArmDirection.DOWN));

		rightStickTrigger.whileHeld(new ControlledDriveStraight());
		rightStickThumbButton.whileHeld(new ControlledApproachTarget());
		rightStick4.whenPressed(new FlipVisionMode());

		leftStickTrigger.whenPressed(new FlipReverseMode());
		
		
  }
}
