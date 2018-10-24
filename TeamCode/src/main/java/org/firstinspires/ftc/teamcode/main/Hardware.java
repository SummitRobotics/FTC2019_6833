package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Declare Hardware members.
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor frontLift = null;
    public DcMotor backLift = null;
    //public DcMotor mastLift = null;
    //public Servo handRotate = null;
    //public Servo leftMineralClamp = null;
    //public Servo rightMineralClamp = null;
    public Servo markerDrop = null;


    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    public final double HD_HEX_REV_COUNTS = 1120; // 1120 for 40:1, 560 for 20:1
    public final double DRIVE_GEAR_RATIO = 1; // This is < 1.0 if geared up
    public final double WHEEL_CIRCUMFERENCE = 6 * 3.1415;
    public final double DRIVE_COUNTS_PER_INCH = (HD_HEX_REV_COUNTS * DRIVE_GEAR_RATIO *
            WHEEL_CIRCUMFERENCE);


    public final double HEX_CORE_REV_COUNTS = 288;
    public final double LIFT_GEAR_RATIO = 1; // This is < 1.0 if geared up
    public final double LIFT_COUNTS_PER_ROT = (HEX_CORE_REV_COUNTS * LIFT_GEAR_RATIO);

    // Local OpMode members
    private HardwareMap hwMap = null;

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
        //mastLift = hwMap.get(DcMotor.class, "mastLift");
        //handRotate = hwMap.get(Servo.class, "handRotate");
        //leftMineralClamp = hwMap.get(Servo.class, "leftMineralClamp");
        //rightMineralClamp = hwMap.get(Servo.class, "rightMineralClamp");
        markerDrop = hwMap.get(Servo.class, "markerDrop");


        // Reverse the motor that runs backwards, set servo positions.
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLift.setDirection(DcMotor.Direction.FORWARD);
        backLift.setDirection(DcMotor.Direction.FORWARD);
        //mastLift.setDirection(DcMotor.Direction.FORWARD);
        //handRotate.setPosition(0.5);
        //leftMineralClamp.setPosition(0.5);
        //rightMineralClamp.setPosition(0.5);
        markerDrop.setPosition(0.5);
    }
}
