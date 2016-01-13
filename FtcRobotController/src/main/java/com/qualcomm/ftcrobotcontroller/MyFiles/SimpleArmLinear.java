package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by William Zulueta on 1/12/16.
 */
public class SimpleArmLinear extends LinearOpMode
{
    private static final int SPEED_MAX = 1;
    @Override
    public void runOpMode() throws InterruptedException
    {
        SimpleArmRobot robot = new SimpleArmRobot(hardwareMap, "left", "right", "arm");
        robot.turnLeft(1000, SPEED_MAX);
        robot.waitForTask();
        robot.turnRight(1000, SPEED_MAX);
        robot.moveForward(1000, SPEED_MAX);
        robot.waitForTask();
    }
}
