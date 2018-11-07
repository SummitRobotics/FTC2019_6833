package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="ProtoServoTest", group="Iterative Opmode")
public class ProtoServoTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    //private ServoToggle testToggle = null;
    private Servo testServoLeft = null;
    private Servo testServoRight = null;

    @Override
    public void init() {

        telemetry.addData("Status", "Initializing");

        testServoLeft = hardwareMap.get(Servo.class, "markerDropLeft");
        testServoRight = hardwareMap.get(Servo.class, "markerDropRight");

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

        if (gamepad1.a) {
            testServoLeft.setPosition(0.5);
            testServoRight.setPosition(0.5);
        } else {
            testServoLeft.setPosition(0.9);
            testServoRight.setPosition(0.9);
        }
    }

    @Override
    public void stop() {

        telemetry.addData("Status", "Stopped");
    }

}
