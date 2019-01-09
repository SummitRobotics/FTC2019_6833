package org.firstinspires.ftc.teamcode.autonomous.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

/**
 * The MarkerServo class extends the CoreAction abstract class and represents a marker drop action in the robot.
 * The use of this action should be followed by a WaitForTime action to give the servo time to move.
 */
public class MarkerServo extends CoreAction {

    private double position;
    private Telemetry telemetry;

    /**
     * Constructs a new instance of the MarkerServo class with unique parameters describing the length and type
     * of action to be preformed.
     * @param position The position to move the servo to.
     * @param nextPos The amount of positions the CoreAuto class should move to find the next action.
     */
    public MarkerServo(double position, int nextPos) {

        this.position = position;
        this.nextPos = nextPos;
    }

    /**
     * Initializes the new action.
     * @param robot The robot to send action to.
     * @param telemetry For updating telemetry within actions.
     */
    @Override
    public void actionInit(Hardware robot, Telemetry telemetry) {

        this.robot = robot;
        this.telemetry = telemetry;
    }

    /**
     * Runs the MoveByEncoder action until complete: Sets servo position, then moves to next position.
     * @return Returns nextPos on first run.
     */
    @Override
    public int run() {

        robot.markerDrop.setPosition(position);
        return nextPos;
    }

    /**
     * Ends the action.
     */
    @Override
    public void actionEnd() {}
}
