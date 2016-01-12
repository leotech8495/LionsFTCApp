package com.qualcomm.ftcrobotcontroller.MyFiles;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by WilliamZulueta on 11/21/15.
 */
public abstract class EventOpMode extends OpMode
{
    protected static final int SPEED_MAX = 1;
    protected static final int SPEED_MIN = -1;
    protected enum DPAD {
            UP, DOWN, LEFT, RIGHT
    };

    @Override
    public void init()
    {
    }

    private double adjust(double value)
    {
        return scaleInput((Range.clip(value, SPEED_MIN, SPEED_MAX)));
    }

    @Override
    public void loop()
    {
        checkGamepad(gamepad1);
        checkGamepad(gamepad2);
    }

    private void checkGamepad(Gamepad gamepad)
    {
        onTrigger(gamepad, adjust(gamepad.left_trigger), adjust(gamepad.right_trigger));
        onLeftStick(gamepad, adjust(gamepad.left_stick_x), adjust(gamepad.left_stick_y));
        onRightStick(gamepad, adjust(gamepad.right_stick_x), adjust(gamepad.right_stick_y));
        if (gamepad.a)
        {
            onAPressed(gamepad);
        } else if (gamepad.b)
        {
            onBPressed(gamepad);
        } else if (gamepad.x)
        {
            onXPressed(gamepad);
        } else if (gamepad.y)
        {
            onYPressed(gamepad);
        } else if (gamepad.right_bumper)
        {
            onRightBumper(gamepad);
        } else if (gamepad.left_bumper)
        {
            onLeftBumper(gamepad);
        } else if (gamepad.dpad_down)
        {
            onDPAD(gamepad, DPAD.DOWN);
        } else if (gamepad.dpad_left)
        {
            onDPAD(gamepad, DPAD.LEFT);
        } else if (gamepad.dpad_right)
        {
            onDPAD(gamepad, DPAD.RIGHT);
        } else if (gamepad.dpad_up)
        {
            onDPAD(gamepad, DPAD.UP);
        }
    }

    public void onDPAD(Gamepad gamepad, DPAD direction)
    {
    }

    public void onLeftBumper(Gamepad gamepad)
    {

    }

    public void onRightBumper(Gamepad gamepad)
    {

    }

    /**
     * Gamepad pressed
     * @param gamepad
     * speed of joystick x
     * @param speedX
     * speed of joystick y
     * @param speedY
     */
    public void onRightStick(Gamepad gamepad, double speedX, double speedY)
    {
    }

    /**
     * Gamepad pressed
     * @param gamepad
     * speed of joystick x
     * @param speedX
     * speed of joystick y
     * @param speedY
     */
    public void onLeftStick(Gamepad gamepad, double speedX, double speedY)
    {
    }

    public void onTrigger(Gamepad gamepad, double speedLeft, double speedRight)
    {

    }

    /**
     * The gamepad that was pressed
     *
     * @param gamepad
     */
    public void onYPressed(Gamepad gamepad)
    {

    }

    /**
     * The gamepad that was pressed
     *
     * @param gamepad
     */
    public void onXPressed(Gamepad gamepad)
    {

    }

    /**
     * The gamepad that was pressed
     *
     * @param gamepad
     */
    public void onBPressed(Gamepad gamepad)
    {

    }

    /**
     * The gamepad that was pressed
     *
     * @param gamepad
     */
    public void onAPressed(Gamepad gamepad)
    {

    }

    /**
     * PREBUILT FUNCTION
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    private double scaleInput(double dVal)
    {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0)
        {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16)
        {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0)
        {
            dScale = -scaleArray[index];
        } else
        {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}
