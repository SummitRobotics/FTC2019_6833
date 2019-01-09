package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name="ProtoServoTest", group="Iterative Opmode")
public class ProtoServoTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Servo testServo = null;

    @Override
    public void init() {
        // Initialize servo
        testServo = hardwareMap.get(Servo.class, "testServo");

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() { }

    @Override
    public void start() { runtime.reset(); }

    @Override
    public void loop() {
        // Set servo position based on game pad "a" button
        if (gamepad1.a) {
            testServo.setPosition(0.5);
        } else {
            testServo.setPosition(0.9);
        }

        telemetry.addData("Status", "Running");
        telemetry.addData("Servo Position", testServo.getPosition());
    }

    @Override
    public void stop() { telemetry.addData("Status", "Stopped"); }
}
