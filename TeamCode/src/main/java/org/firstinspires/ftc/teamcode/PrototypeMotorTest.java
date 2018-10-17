package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class PrototypeMotorTest extends OpMode {

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

        double power;

        double drive = gamepad1.left_trigger - gamepad1.right_trigger;

        power = Range.clip(drive, -1.0, 1.0) ;

        testMotor.setPower(power);
        }

    @Override
    public void stop() {

        telemetry.addData("Status", "Stopped");
    }

}
