package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by ShirleyZulueta on 11/19/15.
 */
public class MyColorSensorDriver extends LinearOpMode
{
    private static final int MIN_BLUE = 200;
    private static final int MIN_RED = 200;
    private ColorSensor mColorSensor;
    /*
    DO NOT ENABLE LED
    Throws values off
     */
    private boolean enabled = false;

    @Override
    public void runOpMode() throws InterruptedException
    {
        // connect sensor
        mColorSensor = hardwareMap.colorSensor.get("nxt");
        // wait for sensor to start
        waitForStart();
        mColorSensor.enableLed(enabled);
        while (opModeIsActive())
        {
//            if (gamepad1.a)
//            {
//                if (enabled)
//                    enabled = false;
//                else
//                    enabled = true;
//                mColorSensor.enableLed(enabled);
//            }
//            telemetry.addData("ColorSensorBlue", "Blue : " + mColorSensor.blue());
//            telemetry.addData("ColorSensorRed", "Red : " + mColorSensor.red());
            // check for blue
            if (mColorSensor.blue() > mColorSensor.red())
            {
                telemetry.addData("ColorSensorColor", "Object being read is blue");
            } else
            {
                telemetry.addData("ColorSensor", "Object being read is red");
            }
            // wait for next read
            waitForNextHardwareCycle();
        }
    }
}
