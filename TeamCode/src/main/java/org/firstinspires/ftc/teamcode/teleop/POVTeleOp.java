package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.main.Hardware;

/**
 * POVTeleOp is the main teleop class. This opmode uses the 5 main FTC functions in its operation: init, init_loop,
 * start, loop, and stop. The main features of this teleop program are as follows:<br>
 * Set drive motor power based on triggers for forward movement and joystick for turning.<br>
 * Adjust drive motor power using either the logPower or exPower functions (explained later).<br>
 * Set lift power using joystick.<br><br>
 *
 * This autonomous program has added features that give the robot more functionality and efficiency,
 * while still retaining simple and intuitive controls.
 */
@TeleOp(name="POVTeleOp", group="Iterative Opmode")
public class POVTeleOp extends OpMode{

    private Hardware robot = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {
        // Initialize all hardware
        robot.init(hardwareMap);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() { }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;
        double liftPower;

        // Get gamepad inputs
        double drive = gamepad1.right_trigger - gamepad1.left_trigger;
        drive = exPower(drive);

        double turn = gamepad1.left_stick_x;

        // Set power variables
        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);
        liftPower = Range.clip(-gamepad1.right_stick_y, -1.0, 1.0);

        // Send calculated power to hardware
        robot.leftDrive.setPower(leftPower);
        robot.rightDrive.setPower(rightPower);
        robot.liftMotor.setPower(liftPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.addData("Lift", liftPower);
    }

    @Override
    public void stop() { telemetry.addData("Status", "Stopped"); }

    /**
     * This function is used to take the power from a controller input and return a logarithmic function
     * of this value. This function gives the robot more sensitivity at higher powers, and increases
     * acceleration.
     * @param power The controller input to be used.
     * @return The output of the logarithmic function.
     */
    private double logPower(double power) {

        if (power >= 0) {
            return 0.96 * Math.log10(power + 0.1) + 0.96;
        } else {
            return -0.96 * Math.log10(-power + 0.1) - 0.96;
        }
    }

    /**
     * This function is used to take the power from a joystick input and return an exponential function
     * of this value. This function gives the robot more sensitivity at lower powers, and increases
     * acceleration at higher powers.
     * @param power The controller input to be used.
     * @return The output of the exponential function.
     */
    private double exPower(double power) {
        return power * power * (power / Math.abs(power));
    }

}