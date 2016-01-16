package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;

public class SimpleArmOpMode extends EventOpMode
{
    /////////////////////////////////////////////////////////
    //////5953 OpMode////////////////////////////////////////
    ////////////////////////////////////////////////////////
    private SimpleArmRobot robot;

    @Override
    public void start()
    {
        robot = new SimpleArmRobot(hardwareMap, "drive", "extending", "arm");
    }

    @Override
    public void onAPressed(Gamepad gamepad)
    {
//        if (gamepad == gamepad1)
//        {
//            robot.moveArmFor(500, 0.2);
//        }
    }

    @Override
    public void onAPressed(Gamepad gamepad, boolean pressed)
    {
        if (pressed && gamepad == gamepad1)
        {
            robot.setArmSpeed(0.2);
        } else
        {
            robot.setArmSpeed(0);
        }
    }

    @Override
    public void stop()
    {
        // stop robot just in case its still running
        robot.setSpeed(0);
        robot.setArmSpeed(0);
    }


    @Override
    public void onLeftStick(Gamepad gamepad, double speedX, double speedY)
    {
        // on left stick move robot
        if (gamepad == gamepad1)
        {
            robot.setLeftSpeed(speedY);
            telemetry.addData("StickLeft", speedY);
        }

        if (gamepad == gamepad2)
        {
            robot.moveServo(1, speedY);
        }
    }

    @Override
    public void onRightStick(Gamepad gamepad, double speedX, double speedY)
    {
        // on right stick move robot
        if (gamepad == gamepad1)
        {
            robot.setRightSpeed(speedY);
            telemetry.addData("StickRight", speedY);
        }

        if (gamepad == gamepad2)
        {
            robot.moveServo(2, speedY);
        }
    }

    @Override
    public void onTrigger(Gamepad gamepad, double speedLeft, double speedRight)
    {
        // on Trigger move main arm up/down
        if (gamepad == gamepad1)
        {
            if (speedLeft != 0)
            {
                robot.setExtendingSpeed(speedLeft);
            } else if (speedRight != 0)
            {
                robot.setExtendingSpeed(-speedRight);
            } else
            {
                robot.setExtendingSpeed(0);
            }
        }

        if (gamepad == gamepad2)
        {
            if (speedLeft != 0)                 // if left trigger is pressed
            {
                robot.setArmSpeed(0.2);
                telemetry.addData("TriggerLeft", speedLeft);
            }
            else if (speedRight != 0)           // if right trigger is pressed
            {
                robot.setArmSpeed(-0.2);
                telemetry.addData("TriggerRight", speedRight);
            }
            else                                // no trigger is pressed
                robot.setArmSpeed(0);
        }
    }
}
