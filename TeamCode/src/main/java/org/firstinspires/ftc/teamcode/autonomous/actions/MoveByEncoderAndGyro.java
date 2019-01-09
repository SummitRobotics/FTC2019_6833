package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

/**
 * The MoveByEncoderAndGyro class extends the CoreAction abstract class and represents a move using encoders
 * and gyros action in the robot. This action has not yet been completed, and is not in use on the robot as
 * of 01-01-19.
 */
public class MoveByEncoderAndGyro extends CoreAction {

    private double speed;
    private int mode, ticks;

    // Direction variables
    private static final int FORWARD = 1,  TURN = -1;

    /**
     *
     * Constructs a new instance of the MoveByEncoderAndGyro class with unique parameters describing the length and type
     * of action to be preformed.
     *
     * @param distance The distance (in inches or radian, depending on mode) to move.
     * @param speed The speed to move at.
     * @param mode The type of move (Forward = 2, Turn = 0, Left = 2, Right = 3).
     * @param nextPos The amount of positions the CoreAuto class should move to find the next action.
     */
    public MoveByEncoderAndGyro (double distance, double speed, int mode, int nextPos) {

        this.speed = speed;
        this.mode = mode;
        this.nextPos = nextPos;

        if (this.mode == FORWARD) {
            this.ticks = (int) (distance * robot.DRIVE_COUNTS_PER_INCH);

        } else {
            this.ticks = (int) (distance * robot.DRIVE_COUNTS_PER_RADIAN);
        }
    }

    /**
     * Initializes the new action.
     * @param robot The robot to send action to.
     * @param telemetry For updating telemetry within actions.
     */
    @Override
    public void actionInit(Hardware robot, Telemetry telemetry) {

        this.robot = robot;

        // Prepare motors for encoder movement
        robot.leftDrive.setTargetPosition(robot.leftDrive.getCurrentPosition() + ticks);
        robot.rightDrive.setTargetPosition(robot.rightDrive.getCurrentPosition() + (mode * ticks));

        // Turn On RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Runs the MoveByEncoderAndGyro action until complete:
     * @return Returns 0 if action is incomplete, returns distance between current index and next
     * index when action is complete
     */
    @Override
    public int run() {
        // Set motor power until finished
        robot.leftDrive.setPower(speed);
        robot.rightDrive.setPower(mode * speed);

        if ( !robot.leftDrive.isBusy() && !robot.rightDrive.isBusy() ) {
            return nextPos;
        }

        return 0;
    }

    /**
     * Ends the action.
     */
    @Override
    public void actionEnd() {

        // Set power to 0
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
