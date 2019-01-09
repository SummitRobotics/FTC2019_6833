package org.firstinspires.ftc.teamcode.main;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Hardware class is used to access all components of the robot throughout the program. New instances
 * of the robot class can be created, or older ones can be used to access hardware components. Because
 * our robot is not very large, we only have a single class for our robot, rather than having a class
 * for each subsystem.
 */
public class Hardware {

    /**
     * Left robot motor.
     */
    public DcMotor leftDrive = null;
    /**
     * Right robot motor.
     */
    public DcMotor rightDrive = null;
    /**
     * Motor to lift the robot mast.
     */
    public DcMotor liftMotor = null;
    /**
     * Servo to drop the team marker.
     */
    public Servo markerDrop = null;

    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    private final int HD_HEX_REV_COUNTS = 1120; // 1120 for 40:1, 560 for 20:1
    private final double DRIVE_GEAR_RATIO = 15.0/26.0; // WheelGear / MotorGear
    private final double WHEEL_CIRCUMFERENCE = 3.543 * Math.PI;
    private final double ROBOT_WIDTH = 13;

    /**
     * This variable contains the number of times the encoder will detect a tick for every inch the
     * robot travels, based on gear ratios, wheel sizes, and encoder ticks per motor rotation.
     */
    public final int DRIVE_COUNTS_PER_INCH = (int) (HD_HEX_REV_COUNTS * DRIVE_GEAR_RATIO /
            WHEEL_CIRCUMFERENCE);

    /**
     * This variable contains the number of times the encoder will detect a tick for every radian the
     * robot turns, based on gear ratios, wheel sizes, robot width,  and encoder ticks per motor rotation.
     */
    public final int DRIVE_COUNTS_PER_RADIAN = (int) -(ROBOT_WIDTH / 2 * DRIVE_COUNTS_PER_INCH);

    private final double HEX_CORE_REV_COUNTS = 288;
    private final double LIFT_GEAR_RATIO = 1; // This is < 1.0 if geared up

    /**
     * This variable contains the number of times the encoder will detect a tick for every rotation the
     * the mast spins , based on gear ratios and encoder ticks per motor rotation.
     */
    public final double LIFT_COUNTS_PER_ROT = (HEX_CORE_REV_COUNTS * LIFT_GEAR_RATIO);

    /**
     * The hardware map used to find motor location in ports.
     */
    public HardwareMap hardwareMap = null;

    // Constructor

    /**
     * Constructs a new robot, but doesn't do anything with is yet.
     */
    public Hardware() {}

    /**
     * Initializes a new robot. Finds hardware components using hardwareMap and sets motor directions.
     * @param hardwareMap the HardwareMap used to find motor locations and names.
     */
    public void init(HardwareMap hardwareMap) {
        // Save reference to Hardware map
        this.hardwareMap = hardwareMap;

        //Init hardware
        leftDrive = this.hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = this.hardwareMap.get(DcMotor.class, "rightDrive");
        liftMotor = this.hardwareMap.get(DcMotor.class, "liftMotor");
        markerDrop = this.hardwareMap.get(Servo.class, "markerDrop");

        // Reverse the motor that runs backwards, set servo positions.
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
