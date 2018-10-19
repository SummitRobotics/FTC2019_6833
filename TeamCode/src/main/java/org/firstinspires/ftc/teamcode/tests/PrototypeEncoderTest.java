package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="PrototypeEncoderTest", group="Iterative Opmode")
public class PrototypeEncoderTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor testMotor = null;


    @Override
    public void init() {

        telemetry.addData("Status", "Initializing");

        testMotor = hardwareMap.get(DcMotor.class, "testMotor");

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

        testMotor.setTargetPosition(500);

        telemetry.addData("Is Busy", testMotor.isBusy());
        telemetry.addData("CurrentLocation", testMotor.getCurrentPosition());
    }

    @Override
    public void stop() {

        telemetry.addData("Status", "Stopped");
    }

}
