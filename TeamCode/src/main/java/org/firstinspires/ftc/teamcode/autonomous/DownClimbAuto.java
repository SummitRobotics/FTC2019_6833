package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.hardware.DcMotor;

public class DownClimbAuto {

    DcMotor backLift = null;
    DcMotor frontLift = null;
    DcMotor leftDrive = null;
    DcMotor rightDrive = null;

    DownClimbAuto(DcMotor backLift, DcMotor frontLift, DcMotor leftDrive, DcMotor rightDrive) {

        this.backLift = backLift;
        this.frontLift = frontLift;
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
    }

    public void run() {

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }
}
