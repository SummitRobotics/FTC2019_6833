package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Declare OpMode members.
    DcMotor leftDrive = null;
    DcMotor rightDrive = null;
    DcMotor frontLift = null;
    DcMotor backLift = null;
    DcMotor mastLift = null;
    Servo handRotate = null;
    Servo leftMineralClamp = null;
    Servo rightMineralClamp = null;


    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    final double HD_HEX_REV_COUNTS = 1120; // 1120 for 40:1, 560 for 20:1
    final double DRIVE_GEAR_RATIO = 1; // This is < 1.0 if geared up
    final double WHEEL_CIRCUMFERENCE = 6 * 3.1415;
    final double DRIVE_COUNTS_PER_INCH = (HD_HEX_REV_COUNTS * DRIVE_GEAR_RATIO * WHEEL_CIRCUMFERENCE);


    final double HEX_CORE_REV_COUNTS = 288;
    final double LIFT_GEAR_RATIO = 1; // This is < 1.0 if geared up
    final double LIFT_COUNTS_PER_ROT = (HEX_CORE_REV_COUNTS * LIFT_GEAR_RATIO);

    // Local OpMode members
    HardwareMap hwMap = null;

    // Constructor
    public Hardware() {}

    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        //Init hardware
        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        frontLift = hwMap.get(DcMotor.class, "frontLift");
        backLift = hwMap.get(DcMotor.class, "backLift");
        mastLift = hwMap.get(DcMotor.class, "mastLift");
        handRotate = hwMap.get(Servo.class, "handRotate");
        leftMineralClamp = hwMap.get(Servo.class, "leftMineralClamp");
        rightMineralClamp = hwMap.get(Servo.class, "rightMineralClamp");

        // Reverse the motor that runs backwards
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLift.setDirection(DcMotor.Direction.FORWARD);
        backLift.setDirection(DcMotor.Direction.FORWARD);
        mastLift.setDirection(DcMotor.Direction.FORWARD);
        handRotate.setPosition(0.5);
        leftMineralClamp.setPosition(0.5);
        rightMineralClamp.setPosition(0.5);
    }
}
