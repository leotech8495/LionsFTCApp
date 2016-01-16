package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ShirleyZulueta on 12/3/15.
 */
public class SimpleArmRobot extends Robot
{
    private DcMotorController mArm;
    private DcMotorController mExtendingMotors;
    private DcMotorController mDriveMotors;

    private ServoController mServoClaw;

    private boolean reverseFrontRight = false;
    private boolean reverseFrontLeft = false;
    private boolean reverseExtendingRight = false;
    private boolean reverseExtendingLeft = false;

    public SimpleArmRobot(HardwareMap hardwareMap, String driveMotors, String extendingMotors, String extender)
    {
        // Connecting motors through legacy module
        mArm = hardwareMap.dcMotorController.get(extender);

        mExtendingMotors = hardwareMap.dcMotorController.get(extendingMotors);
        mDriveMotors = hardwareMap.dcMotorController.get(driveMotors);
        // Changing run mode so we can use it (default its in "nxt mode")
        // switch to write mode, using legacy module only allows write or read at one time
        mArm.setMotorChannelMode(1, DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        mArm.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        mExtendingMotors.setMotorChannelMode(1, DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        mExtendingMotors.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        mDriveMotors.setMotorChannelMode(1, DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        mDriveMotors.setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);

        mServoClaw = hardwareMap.servoController.get("claw");

        timer = new Timer();
    }

    public void moveServo(int number, double position)
    {
        mServoClaw.setServoPosition(number, position);
    }

    @Override
    public void setRightSpeed(double speed)
    {
        if (running)
            return;
        // TODO Figure out which side is which

        mDriveMotors.setMotorPower(1, (isReverseExtendingRight() ? -speed : speed));
//        mRightSide.setMotorPower(2, (isReverseFrontRight() ? -speed : speed));
    }

    @Override
    public void setLeftSpeed(double speed)
    {
        if (running)
            return;

        mDriveMotors.setMotorPower(2, (isReverseFrontLeft()) ? -speed : speed);
//        mLeftSide.setMotorPower(2, (isReverseFrontLeft() ? -speed : speed));
    }

    public void setExtendingSpeed(double speed)
    {
        if (running)
            return;

        mExtendingMotors.setMotorPower(1, (isReverseExtendingRight() ? -speed : speed));
        mExtendingMotors.setMotorPower(2, (isReverseExtendingLeft()) ? -speed : speed);
    }

    @Override
    public void setSpeed(double speed)
    {
        setRightSpeed(speed);
        setLeftSpeed(speed);
    }

    public void setArmSpeed(double speed)
    {
        mArm.setMotorPower(1, speed);
    }

    public void moveArmFor(int seconds, double speed)
    {
        setArmSpeed(speed);
        if (!running)
        {
            running = true;
            timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    setArmSpeed(0);
                    running = false;
                }
            }, seconds);
        }
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

    public boolean isReverseExtendingRight() {
        return reverseExtendingRight;
    }

    public void setReverseExtendingRight(boolean reverseExtendingRight) {
        this.reverseExtendingRight = reverseExtendingRight;
    }

    public boolean isReverseExtendingLeft() {
        return reverseExtendingLeft;
    }

    public void setReverseExtendingLeft(boolean reverseExtendingLeft) {
        this.reverseExtendingLeft = reverseExtendingLeft;
    }
}
