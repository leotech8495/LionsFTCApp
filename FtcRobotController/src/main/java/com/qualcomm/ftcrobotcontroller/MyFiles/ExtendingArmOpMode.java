package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by WilliamZulueta on 12/3/15.
 */
public class ExtendingArmOpMode extends EventOpMode
{
    /////////////////////////////////////////////////////////
    /////8495 OpMode////////////////////////////////////////
    ////////////////////////////////////////////////////////
    private ExtendingArmRobot robot;

    @Override
    public void start()
    {
        // create new 2 arm robot
        robot = new ExtendingArmRobot(hardwareMap, "left_side", "right_side", "extending");
        robot.setReverseFrontLeft(true);
    }

    @Override
    public void stop()
    {
//         stop robot just in case its still running
        robot.setSpeed(0);
        robot.setArmSpeed(0);
    }

    @Override
    public void onLeftStick(Gamepad gamepad, double speedX, double speedY)
    {
        // use left stick to use robot
        if (gamepad == gamepad1)
        {
            robot.setLeftSpeed(speedY);
            telemetry.addData("StickLeft", speedY);
        }
    }

    @Override
    public void onRightStick(Gamepad gamepad, double speedX, double speedY)
    {
        // use right stick to move robot
        if (gamepad == gamepad1)
        {
            robot.setRightSpeed(speedY);
            telemetry.addData("StickRight", speedY);
        } else if (gamepad == gamepad2)
        {
            robot.setExtendingArmSpeed(speedY);
            telemetry.addData("StickRight", speedY);
        }
    }

    @Override
    public void onTrigger(Gamepad gamepad, double speedLeft, double speedRight)
    {
        if (gamepad == gamepad1)
        {

        }

        // on Trigger move main arm up/down
        if (gamepad == gamepad2)
        {
            if (speedLeft != 0)                 // if left trigger is pressed
            {
                robot.setArmSpeed(0.5);
                telemetry.addData("TriggerLeft", speedLeft);
            }
            else if (speedRight != 0)           // if right trigger is pressed
            {
                robot.setArmSpeed(-0.5);
                telemetry.addData("TriggerRight", speedRight);
            }
            else                                // no trigger is pressed
                robot.setArmSpeed(0);
        }
    }
}
