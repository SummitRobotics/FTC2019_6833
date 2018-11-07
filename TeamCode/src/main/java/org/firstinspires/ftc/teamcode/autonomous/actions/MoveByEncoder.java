package org.firstinspires.ftc.teamcode.autonomous.actions;


import com.qualcomm.robotcore.hardware.DcMotor;

public class MoveByEncoder implements CoreAction {


    private double speed;
    private int mode, ticks;
    private Integer nextPos;

    public MoveByEncoder(double distance, double speed, int mode, Integer nextPos) {

        this.speed = speed;
        this.mode = mode;
        this.nextPos = nextPos;

        this.ticks = (int)(distance * robot.DRIVE_COUNTS_PER_INCH);
    }

    @Override
    public void runInit() {

        robot.leftDrive.setTargetPosition(robot.leftDrive.getCurrentPosition() + ticks);
        robot.rightDrive.setTargetPosition(robot.rightDrive.getCurrentPosition() + mode * ticks);

        // Turn On RUN_TO_POSITION
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public Integer run() {

        robot.leftDrive.setPower(speed);
        robot.rightDrive.setPower(mode * speed);

        if ( !robot.leftDrive.isBusy() && !robot.rightDrive.isBusy() ) {

            killRobot();

            return nextPos;
        }

        return 0;
    }

    private void killRobot() {

        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
