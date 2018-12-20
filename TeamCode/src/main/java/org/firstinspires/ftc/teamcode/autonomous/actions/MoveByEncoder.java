package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class to move forward or turn
public class MoveByEncoder extends CoreAction {

    private double speed;
    private int mode, ticks;
    private int nextPos, leftTarget, rightTarget;

    // Direction variables
    public static final int FORWARD = 1,  TURN = -1, LEFT = 2, RIGHT = 3;

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

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);

        // Prepare motors for encoder movement
        if (mode == FORWARD || mode == TURN) {
            leftTarget = robot.leftDrive.getCurrentPosition() + ticks;
            rightTarget = robot.rightDrive.getCurrentPosition() + (mode * ticks);

        } else if (mode == LEFT){
            leftTarget = -(robot.leftDrive.getCurrentPosition() + ticks);
            rightTarget = robot.rightDrive.getCurrentPosition();

        } else if (mode == RIGHT) {
            leftTarget = robot.leftDrive.getCurrentPosition();
            rightTarget = -(robot.rightDrive.getCurrentPosition() + ticks);
        }

        robot.leftDrive.setTargetPosition(leftTarget);
        robot.rightDrive.setTargetPosition(rightTarget);

        // Turn On RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

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

    @Override
    public void actionEnd() {

        // Set power to 0
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
