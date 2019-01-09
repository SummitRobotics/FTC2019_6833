package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp(name="ProtoMotorTest", group="Iterative Opmode")
public class ProtoMotorTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor testMotor = null;


    @Override
    public void init() {
        // Initialize motor
        testMotor = hardwareMap.get(DcMotor.class, "test");

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
        // Set motor power based on triggers.
        double drive = gamepad1.left_trigger - gamepad1.right_trigger;
        double power = Range.clip(drive, -1.0, 1.0) ;

        testMotor.setPower(power);

        telemetry.addData("Status", "Running");
        telemetry.addData("Motor Power", power);
        }

    @Override
    public void stop() { telemetry.addData("Status", "Stopped"); }

}
