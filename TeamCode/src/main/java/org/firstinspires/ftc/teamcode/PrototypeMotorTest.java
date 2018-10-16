package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class PrototypeMotorTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    /*
    //Spliced code

    private DcMotor leftMotors = null;
    private DcMotor rightMotors = null;

    private DcMotor wheelRotate = null;
    private DcMotor mastLift = null;
    */

    //Unspliced code

    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;


    @Override
    public void init() {

        telemetry.addData("Status", "Initializing");

        /*
        //Spliced code

        leftMotors = hardwareMap.get(DcMotor.class, "leftMotors");
        rightMotors = hardwareMap.get(DcMotor.class, "rightMotors");

        wheelRotate = hardwareMap.get(DcMotor.class, "wheelRotate");
        mastLift = hardwareMap.get(DcMotor.class, "mastLift");

        leftMotors.setDirection(DcMotor.Direction.FORWARD);
        rightMotors.setDirection(DcMotor.Direction.REVERSE);

        wheelRotate.setDirection(DcMotor.Direction.FORWARD);
        wheelRotate.setDirection(DcMotor.Direction.FORWARD);
        */

        //Unspliced code

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);


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

        double leftMotorPower;
        double rightMotorPower;

        double drive = gamepad1.left_trigger - gamepad1.right_trigger;
        double turn = gamepad1.left_stick_x;

        leftMotorPower = Range.clip(drive + turn, -1.0, 1.0) ;
        rightMotorPower = Range.clip(drive - turn, -1.0, 1.0) ;

        //Unspliced code

        leftFront.setPower(leftMotorPower);
        rightFront.setPower(rightMotorPower);
        leftBack.setPower(leftMotorPower);
        rightBack.setPower(rightMotorPower);


        /*
        //Spliced code

        double wheels = gamepad1.right_stick_y;
        double mastPower = gamepad1.right_stick_x;

        leftMotors.setPower(leftMotorPower);
        rightMotors.setPower(rightMotorPower);

        wheelRotate.setPower(wheels);
        mastLift.setPower(mastPower);
        */
        }

    @Override
    public void stop() {

        telemetry.addData("Status", "Stopped");
    }

}
