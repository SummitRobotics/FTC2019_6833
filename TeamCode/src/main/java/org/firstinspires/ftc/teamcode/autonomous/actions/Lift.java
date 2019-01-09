package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

/**
 * The Lift class extends the CoreAction abstract class and represents a mast lift action in the robot.
 */
public class Lift extends CoreAction {

    private double speed;
    private int ticks;
    private Telemetry telemetry;

    /**
     * Constructs a new instance of the Lift class with unique parameters describing the length and type
     * of action to be preformed.
     * @param distance The amount of rotations to turn the lift motor.
     * @param speed The speed to turn the lift motor at.
     * @param nextPos The amount of positions the CoreAuto class should move to find the next action.
     */
    public Lift(double distance, double speed, int nextPos) {

        // Setup variables for use in action
        this.speed = speed;
        this.nextPos = nextPos;

        // Ticks is the encoder ticks used in motion
        this.ticks = (int)(distance * robot.LIFT_COUNTS_PER_ROT);
    }

    /**
     * Initializes the new action: puts the lift motor in run to position mode.
     * @param robot The robot to send action to.
     * @param telemetry For updating telemetry within actions.
     */
    @Override
    public void actionInit(Hardware robot, Telemetry telemetry) {

        this.robot = robot;
        this.telemetry = telemetry;

        // Prepare for running to encoder position
        robot.liftMotor.setTargetPosition(robot.liftMotor.getCurrentPosition() + ticks);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Runs the Lift action until complete: Set power each run, check if action is finished.
     * @return Returns 0 if action is incomplete, returns distance between current index and next
     * index when action is complete
     */
    @Override
    public int run() {

        // Set motor power until finished
        robot.liftMotor.setPower(speed);

        if ( !robot.liftMotor.isBusy() ) {
            return nextPos;

        }

        telemetry.addData("Lift:", "Running to: " + ticks +
                ", Running at: " + robot.liftMotor.getCurrentPosition());
        return 0;
    }

    /**
     * Ends the action: Stop motion.
     */
    @Override
    public void actionEnd() {

        // Set motor power to 0
        robot.liftMotor.setPower(0);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
