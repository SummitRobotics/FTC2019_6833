package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.autonomous.actions.LegControl;
import org.firstinspires.ftc.teamcode.main.Hardware;

@TeleOp(name="POVTeleOp", group="Iterative Opmode")
public class POVTeleOp extends OpMode{

    private Hardware robot = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();
    private LegControl centerLegs = new LegControl(Math.PI, Math.PI, 0.9, 1);
    private boolean centering = false;

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
        double frontLegPower;
        double backLegPower;

        // Get gamepad inputs
        double drive = gamepad1.right_trigger - gamepad1.left_trigger;
        drive = expPower(drive);
        double turn = gamepad1.left_stick_x;

        // Set power variables
        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);

        if (gamepad1.left_bumper) {
            frontLegPower = gamepad1.right_stick_y;
            backLegPower = 0;
        } else if (gamepad1.right_bumper) {
            backLegPower = gamepad1.right_stick_y;
            frontLegPower = 0;
        } else {
            frontLegPower = gamepad1.right_stick_y;
            backLegPower = gamepad1.right_stick_y;
        }

        if (gamepad1.a) {
            robot.frontIntake.setDirection(Servo.Direction.FORWARD);
            robot.backIntake.setDirection(Servo.Direction.REVERSE);
        } else if (gamepad1.b) {
            robot.frontIntake.setDirection(Servo.Direction.REVERSE);
            robot.backIntake.setDirection(Servo.Direction.FORWARD);
        } else {
            robot.frontIntake.setPosition(0.5);
            robot.backIntake.setPosition(0.5);
        }

        // Send calculated power to hardware
        robot.leftFrontDrive.setPower(leftPower);
        robot.leftBackDrive.setPower(leftPower);
        robot.rightFrontDrive.setPower(rightPower);
        robot.rightBackDrive.setPower(rightPower);
        robot.frontLeg.setPower(frontLegPower);
        robot.backLeg.setPower(backLegPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    @Override
    public void stop() { telemetry.addData("Status", "Stopped"); }

    private double logPower(double power) {

        if (power >= 0) {
            return 0.96 * Math.log10(power + 0.1) + 0.96;
        } else {
            return -0.96 * Math.log10(-power + 0.1) - 0.96;
        }
    }

    private double expPower(double power) {
        return (power * power) * (Math.abs(power) / power);
    }

}