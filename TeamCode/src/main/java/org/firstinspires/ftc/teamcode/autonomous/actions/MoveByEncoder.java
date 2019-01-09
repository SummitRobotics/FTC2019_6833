package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

/**
 * The MoveByEncoder class extends the CoreAction abstract class and represents a move action in the robot.
 */
public class MoveByEncoder extends CoreAction {

    private double speed;
    private int mode, ticks;
    private int leftTarget, rightTarget;

    // Direction variables
    public static final int FORWARD = 1,  TURN = -1, LEFT = 2, RIGHT = 3;

    /**
     * Constructs a new instance of the MoveByEncoder class with unique parameters describing the length and type
     * of action to be preformed.
     * @param distance The distance (in inches or radian, depending on mode) to move.
     * @param speed The speed to move at.
     * @param mode The type of move (Forward = 2, Turn = 0, Left = 2, Right = 3).
     * @param nextPos The amount of positions the CoreAuto class should move to find the next action.
     */
    public MoveByEncoder(double distance, double speed, int mode, int nextPos) {

        this.speed = speed;
        this.mode = mode;
        this.nextPos = nextPos;

        if (this.mode == FORWARD) {
            this.ticks = (int) (distance * robot.DRIVE_COUNTS_PER_INCH);

        } else if (this.mode == TURN) {
            this.ticks = (int) (distance * robot.DRIVE_COUNTS_PER_RADIAN);

        } else {
            this.ticks = (int) (distance * robot.DRIVE_COUNTS_PER_RADIAN);
        }
    }

    /**
     * Initializes the new action: Set motor target positions based on mode and put motors in run to position mode.
     * @param robot The robot to send action to.
     * @param telemetry For updating telemetry within actions.
     */
    @Override
    public void actionInit(Hardware robot, Telemetry telemetry) {

        this.robot = robot;

        // Prepare motors for encoder movement
        if (mode == FORWARD || mode == TURN) {
            leftTarget = robot.leftDrive.getCurrentPosition() + ticks;
            rightTarget = robot.rightDrive.getCurrentPosition() + (mode * ticks);

        } else if (mode == LEFT){
            leftTarget = (robot.leftDrive.getCurrentPosition() + ticks);
            rightTarget = robot.rightDrive.getCurrentPosition();

        } else if (mode == RIGHT) {
            leftTarget = robot.leftDrive.getCurrentPosition();
            rightTarget = (robot.rightDrive.getCurrentPosition() + ticks);
        }

        robot.leftDrive.setTargetPosition(leftTarget);
        robot.rightDrive.setTargetPosition(rightTarget);

        // Turn On RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Runs the MoveByEncoder action until complete: Set motor speed, check if motors are finished.
     * @return Returns 0 if action is incomplete, returns distance between current index and next
     * index when action is complete
     */
    @Override
    public int run() {
        // Set motor power until finished
        robot.leftDrive.setPower(speed);
        robot.rightDrive.setPower(mode * speed);

        if ( ((robot.leftDrive.getCurrentPosition() + 11 > leftTarget) && (robot.leftDrive.getCurrentPosition() - 11 < leftTarget))
                && ((robot.rightDrive.getCurrentPosition() + 11 > rightTarget) && (robot.rightDrive.getCurrentPosition() - 11 < rightTarget))) {

            return nextPos;
        }

        return 0;
    }

    /**
     * Ends the action: Stop motion.
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
