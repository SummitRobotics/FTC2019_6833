package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="WaitAndServoTest", group="Linear Opmode")
public class WaitAndServoTest extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Servo testServo = null;

    @Override
    public void runOpMode() {

        testServo = hardwareMap.get(Servo.class, "testServo");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        runtime.reset();

        turnServo(0);

        waitForTime(5);

        turnServo(1);
    }

    private void waitForTime(double time) {

        time += runtime.time();

        while(time >= runtime.time()) {

            telemetry.addData("Status", "Waiting");
            telemetry.addData("Time",time-runtime.time());
            telemetry.update();
        }
    }

    private void turnServo(double newPos) {

        testServo.setPosition(newPos);

        while (testServo.getPosition() > newPos + .1 || testServo.getPosition() < newPos-.1) {

            telemetry.addData("Status", "Waiting");
            telemetry.addData("Position",testServo.getPosition());
            telemetry.update();
        }
    }
}
