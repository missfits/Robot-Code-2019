/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Add your docs here.
 */
public abstract class BetterCommand extends Command{
    protected abstract void betterExecute();
    protected void execute(){
        if(!isFinished()){
            betterExecute();
        }
    }
}
