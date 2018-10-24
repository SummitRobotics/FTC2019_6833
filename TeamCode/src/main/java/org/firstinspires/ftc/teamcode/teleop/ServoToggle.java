package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoToggle {

    // Declare variables
    private Servo servo;
    private double[] positions = new double[2];
    private boolean lastState;
    private int posIndex = 0;

    public ServoToggle(Servo servo, double pos1, double pos2) {

        // Set up toggle positions and other variables
        positions[0] = pos1;
        positions[1] = pos2;

        this.servo = servo;
        this.lastState = false;
    }

    /**
     * Method to toggle the servo between two positions based on input from a button.
     * @param buttonDown The button being used for input.
     */
    public double setServoState(boolean buttonDown) {

        servo.setPosition(positions[this.checkIfToggle(buttonDown)]);
        return positions[this.checkIfToggle(buttonDown)];
    }

    /**
     * Method to change the position being returned only if the button is pressed once. Holding
     *          down the button will do nothing.
     * @param buttonDown The button beign used for input.
     * @return The index of the selected position.
     */
    private int checkIfToggle(boolean buttonDown) {

        if (buttonDown && !lastState) {

            posIndex = 1 - posIndex;
        }

        lastState = buttonDown;

        return posIndex;
    }
}
