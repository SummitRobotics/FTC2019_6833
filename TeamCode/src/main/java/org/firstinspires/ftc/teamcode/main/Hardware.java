package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    // Declare Hardware members.
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor frontLeg;
    public DcMotor backLeg;
    public Servo frontIntake;
    public Servo backIntake;
    public Servo markerDrop;

    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    private final int HD_HEX_REV_COUNTS = 1120; // 1120 for 40:1, 560 for 20:1
    private final double DRIVE_GEAR_RATIO = 15.0/26.0; // WheelGear / MotorGear
    private final double WHEEL_CIRCUMFERENCE = 3.543 * Math.PI;
    public final double ROBOT_WIDTH = 13;

    public final int DRIVE_COUNTS_PER_INCH = (int) (HD_HEX_REV_COUNTS * DRIVE_GEAR_RATIO /
            WHEEL_CIRCUMFERENCE);

    public final int DRIVE_COUNTS_PER_RADIAN = (int) -(ROBOT_WIDTH / 2 * DRIVE_COUNTS_PER_INCH);

    private final double HEX_CORE_REV_COUNTS = 288;
    private final double LEG_GEAR_RATIO = 1; // This is < 1.0 if geared up
    public final double LEG_COUNTS_PER_ROT = (HEX_CORE_REV_COUNTS * LEG_GEAR_RATIO);

    // Local opmode hardware map
    private HardwareMap hardwareMap = null;

    // Constructor
    public Hardware() {}

    public void init(HardwareMap hardwareMap) {
        // Save reference to Hardware map
        this.hardwareMap = hardwareMap;

        //Init hardware
        leftDrive = this.hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = this.hardwareMap.get(DcMotor.class, "rightDrive");
        frontLeg = this.hardwareMap.get(DcMotor.class, "frontLeg");
        backLeg = this.hardwareMap.get(DcMotor.class, "backLeg");
        frontIntake = this.hardwareMap.get(Servo.class, "frontIntake");
        backIntake = this.hardwareMap.get(Servo.class, "backIntake");
        markerDrop = this.hardwareMap.get(Servo.class, "markerDrop");

        // Reverse the motor that runs backwards, set servo positions.
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeg.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeg.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
