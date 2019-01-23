package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.autonomous.actions.LegControl;
import org.firstinspires.ftc.teamcode.main.Hardware;

@TeleOp(name="LegTest", group="Iterative Opmode")
public class LegTest extends OpMode{

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeg;
    private DcMotor backLeg;

    @Override
    public void init() {
        // Initialize all hardware
        frontLeg = hardwareMap.get(DcMotor.class, "frontLeg");
        backLeg = hardwareMap.get(DcMotor.class, "backLeg");
        frontLeg.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeg.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

        double frontLegPower;
        double backLegPower;

        // Set power variables
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

            frontLeg.setPower(frontLegPower);
            backLeg.setPower(backLegPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Legs", "front (%.2f), back (%.2f)", frontLegPower, backLegPower);
    }

    @Override
    public void stop() { telemetry.addData("Status", "Stopped"); }
}