package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ShirleyZulueta on 12/3/15.
 */
public class ExtendingArmRobot extends Robot
{
    private DcMotorController mExtendingArm;

    private DcMotorController mLeftSide;
    private DcMotorController mRightSide;

    private boolean reverseBackLeft = false;
    private boolean reverseBackRight = false;
    private boolean reverseFrontRight = false;
    private boolean reverseFrontLeft = false;

    public ExtendingArmRobot(HardwareMap hardwareMap, String leftSide, String rightSide, String extender)
    {
        // Connecting motors through legacy module
        mExtendingArm = hardwareMap.dcMotorController.get(extender);

        mLeftSide = hardwareMap.dcMotorController.get(leftSide);
        mRightSide = hardwareMap.dcMotorController.get(rightSide);
        // Changing run mode so we can use it (default its in "nxt mode")
        // switch to write mode, using legacy module only allows write or read at one time
        mExtendingArm.setMotorChannelMode(1, DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        mExtendingArm.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        mLeftSide.setMotorChannelMode(1, DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        mLeftSide.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        mRightSide.setMotorChannelMode(1, DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        mRightSide.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        timer = new Timer();
    }

    @Override
    public void setRightSpeed(double speed)
    {
        if (running)
            return;
        // TODO Figure out which side is which

        mRightSide.setMotorPower(1, (isReverseBackRight() ? -speed : speed));
        mRightSide.setMotorPower(2, (isReverseFrontRight() ? -speed : speed));
    }

    @Override
    public void setLeftSpeed(double speed)
    {
        if (running)
            return;

        mLeftSide.setMotorPower(1, (isReverseBackLeft()) ? -speed : speed);
        mLeftSide.setMotorPower(2, (isReverseFrontLeft() ? -speed : speed));
    }

    @Override
    public void setSpeed(double speed)
    {
        setLeftSpeed(speed);
        setRightSpeed(speed);
    }

    public void setExtendingArmSpeed(double speed)
    {
        mExtendingArm.setMotorPower(1, speed);
    }

    public void setArmSpeed(double speed)
    {
        mExtendingArm.setMotorPower(2, speed);
    }

    @Override
    public void turnRight(int seconds, double speed)
    {
        if (running)
            return;

        setRightSpeed(speed);
        running = true;
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                running = false;
                setRightSpeed(0);
            }
        }, seconds);
    }

    @Override
    public void turnLeft(int seconds, double speed)
    {
        if (running)
            return;

        setLeftSpeed(speed);
        running = true;
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                running = false;
                setLeftSpeed(0);
            }
        }, seconds);
    }

    @Override
    public void moveForward(int seconds, double speed)
    {
        if (running)
            return;

        setSpeed(1);
        running = true;
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                running = false;
                setSpeed(0);
            }
        }, seconds);
    }

    public boolean isReverseBackLeft()
    {
        return reverseBackLeft;
    }

    public void setReverseBackLeft(boolean reverseBackLeft)
    {
        this.reverseBackLeft = reverseBackLeft;
    }

    public boolean isReverseBackRight()
    {
        return reverseBackRight;
    }

    public void setReverseBackRight(boolean reverseBackRight)
    {
        this.reverseBackRight = reverseBackRight;
    }

    public boolean isReverseFrontRight()
    {
        return reverseFrontRight;
    }

    public void setReverseFrontRight(boolean reverseFrontRight)
    {
        this.reverseFrontRight = reverseFrontRight;
    }

    public boolean isReverseFrontLeft()
    {
        return reverseFrontLeft;
    }

    public void setReverseFrontLeft(boolean reverseFrontLeft)
    {
        this.reverseFrontLeft = reverseFrontLeft;
    }
}
