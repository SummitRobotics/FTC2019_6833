package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="ProtoServoTest", group="Iterative Opmode")
public class ProtoServoTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //private ServoToggle testToggle = null;
    private Servo testServo = null;

    @Override
    public void init() {

        telemetry.addData("Status", "Initializing");

        testServo = hardwareMap.get(Servo.class, "markerDrop");

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {

        telemetry.addData("Status", "Running");

        if (gamepad1.x) {
            testServo.setPosition(0.5);
        } else {
            testServo.setPosition(0.9);
        }
    }

    @Override
    public void stop() {

        telemetry.addData("Status", "Stopped");
    }

}
