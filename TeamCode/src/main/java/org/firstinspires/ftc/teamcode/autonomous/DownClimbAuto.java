package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.main.BasicHardware;

public class DownClimbAuto {

    BasicHardware robot = new BasicHardware();

    DownClimbAuto(com.qualcomm.robotcore.hardware.HardwareMap hwMap) {

        robot.init(hwMap);
    }

    public void run() throws InterruptedException {

        robot.backLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.backLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        int frontTarget = robot.frontLift.getCurrentPosition() + 1 * (int)robot.LIFT_COUNTS_PER_ROT; // 1 needs to be replaced with # of rotations
        int backTarget = robot.backLift.getCurrentPosition() + 1 * (int)robot.LIFT_COUNTS_PER_ROT; // 1 needs to be replaced with # of rotations

        robot.backLift.setTargetPosition(backTarget);
        robot.frontLift.setTargetPosition(frontTarget);

        robot.backLift.setPower(1); // test power val
        robot.frontLift.setPower(1); // test power val

        while (robot.backLift.isBusy() && robot.frontLift.isBusy()) {

            Thread.sleep(100);
        }

        robot.frontLift.setPower(0);
        robot.backLift.setPower(0);

        robot.frontLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
