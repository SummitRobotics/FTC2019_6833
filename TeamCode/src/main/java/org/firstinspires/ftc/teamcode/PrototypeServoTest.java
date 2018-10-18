package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="PrototypeServoTest", group="Iterative Opmode")
public class PrototypeServoTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private ServoToggle testToggle = null;
    private Servo testServo = null;

    @Override
    public void init() {

        telemetry.addData("Status", "Initializing");

        testServo = hardwareMap.get(Servo.class, "testServo");
        testToggle = new ServoToggle(testServo, .3, .7);

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

        testToggle.setServoState(gamepad1.a);
    }

    @Override
    public void stop() {

        telemetry.addData("Status", "Stopped");
    }

}
