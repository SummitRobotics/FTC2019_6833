package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class to move forward or turn
public class MoveByEncoderAndGyro extends CoreAction {

    private double speed;
    private int mode, ticks;
    private int nextPos;

    // Direction variables
    public static final int FORWARD = 1,  TURN = -1;

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

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);

        // Prepare motors for encoder movement
        robot.leftDrive.setTargetPosition(robot.leftDrive.getCurrentPosition() + ticks);
        robot.rightDrive.setTargetPosition(robot.rightDrive.getCurrentPosition() + (mode * ticks));

        // Turn On RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

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

    @Override
    public void actionEnd() {

        // Set power to 0
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
