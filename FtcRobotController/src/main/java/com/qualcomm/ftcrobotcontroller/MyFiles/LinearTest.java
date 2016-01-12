package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by William Zulueta on 1/6/16.
 */
public class LinearTest extends LinearOpMode
{
    private static final int SPEED_MAX = 1;
    private ExtendingArmRobot robot;

    @Override
    public void runOpMode() throws InterruptedException
    {
        // connect
        robot = new ExtendingArmRobot(hardwareMap, "left_side", "right_side", "extending");
        robot.setReverseFrontRight(true);
        robot.setReverseBackRight(true);
        // do stuff
        robot.turnRight(1000, SPEED_MAX);   // turn right 1 second at max speed
        // wait for robot to turn before doing something else, avoid turning right and turning left at same time
        robot.waitForTask();
        robot.turnLeft(1000, SPEED_MAX);    // turn left 1 second at max speed
        robot.waitForTask();
        robot.moveForward(1000, SPEED_MAX); // go forward 1 second at max speed
    }
}
