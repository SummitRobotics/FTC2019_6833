package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="POVTeleOp", group="Iterative Opmode")
public class POVTeleOp extends OpMode{

    Hardware robot = new Hardware();
    private ElapsedTime runtime = new ElapsedTime();

    private ServoToggle handToggle;
    private ServoToggle leftMinToggle;
    private ServoToggle rightMinToggle;


    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");

        robot.init(hardwareMap);
        handToggle = new ServoToggle(robot.handRotate, 0.0, 1.0);
        leftMinToggle = new ServoToggle(robot.leftMineralClamp, 0.4, 0.6);
        rightMinToggle = new ServoToggle(robot.rightMineralClamp, 0.4, 0.6);

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
        double leftPower;
        double rightPower;
        double liftPower;
        double mastPower;

        double drive = gamepad1.left_trigger - gamepad1.right_trigger;
        double turn = gamepad1.left_stick_x;
        double lift = gamepad1.right_stick_y;
        double mast =  gamepad2.left_trigger - gamepad2.right_trigger;
        
        handToggle.setServoState(gamepad2.a);
        leftMinToggle.setServoState(gamepad2.x);
        rightMinToggle.setServoState(gamepad2.b);

        leftPower = Range.clip(drive + turn, -1.0, 1.0);
        rightPower = Range.clip(drive - turn, -1.0, 1.0);
        liftPower = Range.clip(lift, -1.0, 1.0);
        mastPower = Range.clip(mast, -1.0, 1.0);



        // Send calculated power to hardware
        robot.leftDrive.setPower(leftPower);
        robot.rightDrive.setPower(rightPower);
        robot.frontLift.setPower(liftPower);
        robot.backLift.setPower(liftPower);
        robot.mastLift.setPower(mastPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    @Override
    public void stop() { }

}