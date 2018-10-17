package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="AutoProgram", group="Iterative Opmode")
public class AutoProgram extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor frontLift = null;
    private DcMotor backLift = null;
    private DcMotor mastLift = null;

    private int taskCount = 0; // Variable to keep track of task
    private int subTaskCount = 0; // and sub task

    // Prepare variables for encoder use
    // http://www.revrobotics.com/content/docs/Encoder-Guide.pdf
    final double hdHexRevCounts = 1120; // 1120 for 40:1, 560 for 20:1
    final double driveGearRatio = 1; // This is < 1.0 if geared up
    final double wheelCircumference = 6 * 3.1415;
    final double driveCountsPerInch = (hdHexRevCounts * driveGearRatio * wheelCircumference);


    final double hexCoreRevCounts = 288;
    final double liftGearRatio = 1; // This is < 1.0 if geared up
    final double liftCountsPerRot = (hexCoreRevCounts * liftGearRatio);

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        //Init hardware
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        frontLift = hardwareMap.get(DcMotor.class, "frontLift");
        backLift = hardwareMap.get(DcMotor.class, "backLift");
        mastLift = hardwareMap.get(DcMotor.class, "mastLift");

        // Reverse the motor that runs backwards
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLift.setDirection(DcMotor.Direction.FORWARD);
        backLift.setDirection(DcMotor.Direction.FORWARD);
        mastLift.setDirection(DcMotor.Direction.FORWARD);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        if (taskCount == 0) { // Landing
            if (subTaskCount == 0) {
                if (liftLegsInit(0.7, -0.5)) {
                    subTaskCount ++;
                }
            } else if (subTaskCount == 1)  {
                if (liftLegs()) {
                    subTaskCount = 0;
                    taskCount ++;
                }
            }
        } else if (taskCount == 1) { // Sampling
            sample();
        } else if (taskCount == 2) { // Placing marker in Depot
            placeMarker();
        } else if (taskCount == 3) { // Go to crater
            goToCrater();
        }
        telemetry.addData("Task", taskCount + "." + subTaskCount);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        // Stop all motion;
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        frontLift.setPower(0);
        backLift.setPower(0);
        mastLift.setPower(0);
    }

}