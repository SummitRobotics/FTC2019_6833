package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.main.BasicHardware;

public class DownClimbAuto {

    BasicHardware robot = new BasicHardware();

    DcMotor backLift = null;
    DcMotor frontLift = null;
    DcMotor leftDrive = null;
    DcMotor rightDrive = null;

    BasicHardware ahwMap;

    DownClimbAuto(com.qualcomm.robotcore.hardware.HardwareMap hwMap, BasicHardware ahwMap) {

        robot.init(hwMap);

        this.ahwMap = ahwMap;

        backLift = robot.backLift;
        frontLift = robot.frontLift;
        leftDrive = robot.leftDrive;
        rightDrive = robot.rightDrive;
    }

    public void run() throws InterruptedException {

        backLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        int frontTarget = frontLift.getCurrentPosition() + 1 * (int)robot.LIFT_COUNTS_PER_ROT; // 1 needs to be replaced with # of rotations
        int backTarget = backLift.getCurrentPosition() + 1 * (int)robot.LIFT_COUNTS_PER_ROT; // 1 needs to be replaced with # of rotations

        backLift.setTargetPosition(backTarget);
        frontLift.setTargetPosition(frontTarget);

        backLift.setPower(1); // test power val
        frontLift.setPower(1); // test power val

        while (backLift.isBusy() && frontLift.isBusy()) {

            Thread.sleep(100);
        }

        frontLift.setPower(0);
        backLift.setPower(0);

        frontLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
