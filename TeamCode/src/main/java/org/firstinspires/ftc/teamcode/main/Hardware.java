package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Declare Hardware members.
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor liftMotor = null;
    public Servo markerDropLeft = null;
    public Servo markerDropRight = null;




    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    private final double HD_HEX_REV_COUNTS = 1120.0; // 1120 for 40:1, 560 for 20:1
    private final double DRIVE_GEAR_RATIO = 15.0/40.0; // MotorGear / WheelGear
    private final double WHEEL_CIRCUMFERENCE = 6 * 3.1415;
    public final int DRIVE_COUNTS_PER_INCH = (int) (HD_HEX_REV_COUNTS * DRIVE_GEAR_RATIO /
            WHEEL_CIRCUMFERENCE);


    private final double HEX_CORE_REV_COUNTS = 288;
    private final double LIFT_GEAR_RATIO = 1; // This is < 1.0 if geared up
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
        liftMotor = hwMap.get(DcMotor.class, "leftMotor");
        markerDropLeft = hwMap.get(Servo.class, "markerDropLeft");
        markerDropRight = hwMap.get(Servo.class, "markerDropRight");

        // Reverse the motor that runs backwards, set servo positions.
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        markerDropLeft.setPosition(0.5);
        markerDropRight.setPosition(0.5);
    }
}
