package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WilliamZulueta on 11/23/15.
 */
public class Robot
{
    protected DcMotor mBackLeftMotor;
    protected DcMotor mFrontLeftMotor;
    protected DcMotor mFrontRightMotor;
    protected DcMotor mBackRightMotor;
    protected Timer timer;
    protected boolean running = false;

    public Robot()
    {
    }

    public Robot(HardwareMap hardwareMap, String backLeft, String backRight, String frontLeft, String frontRight)
    {
        mBackLeftMotor = hardwareMap.dcMotor.get(backLeft);
        mBackRightMotor = hardwareMap.dcMotor.get(backRight);
        mFrontLeftMotor = hardwareMap.dcMotor.get(frontLeft);
        mFrontRightMotor = hardwareMap.dcMotor.get(frontRight);
        timer = new Timer();
    }


    public TimerTask createNewTask()
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                running = false;
                setSpeed(0);
            }
        };
    }

    public void setSpeed(double speed)
    {
        setRightSpeed(speed);
        setLeftSpeed(speed);
    }

    public void setRightSpeed(double speed)
    {
        if (running)
            return;
        mBackRightMotor.setPower(speed);
        mFrontRightMotor.setPower(speed);
    }

    public void setLeftSpeed(double speed)
    {
        if (running)
            return;
        mBackLeftMotor.setPower(speed);
        mFrontLeftMotor.setPower(speed);
    }

    public void moveForward(int seconds, double speed)
    {
        setSpeed(speed);
        if (!running)
        {
            running = true;
            timer.schedule(createNewTask(), seconds);
        }
    }

    public void turnLeft(int seconds, double speed)
    {
        setLeftSpeed(speed);
        if (!running)
        {
            running = true;
            timer.schedule(createNewTask(), seconds);
        }
    }

    public void turnRight(int seconds, double speed)
    {
        setRightSpeed(speed);
        if (!running)
        {
            running = true;
            timer.schedule(createNewTask(), seconds);
        }
    }

    public void waitForTask()
    {
        while (running)
        {

        }
    }

    public DcMotor getBackLeftMotor()
    {
        return mBackLeftMotor;
    }

    public DcMotor getFrontLeftMotor()
    {
        return mFrontLeftMotor;
    }

    public DcMotor getFrontRightMotor()
    {
        return mFrontRightMotor;
    }

    public DcMotor getBackRightMotor()
    {
        return mBackRightMotor;
    }
}
