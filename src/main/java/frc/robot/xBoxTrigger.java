package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class xBoxTrigger extends Button {
    Joystick controller;
    int port;
    public xBoxTrigger(Joystick controller, int port){
        this.controller = controller;
        this.port = port;
    }
    public boolean get(){
        return controller.getRawAxis(port) > 0.2;
    }

}