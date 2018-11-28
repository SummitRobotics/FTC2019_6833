package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class for lifting mast
public class Lift extends CoreAction {

    private double speed;
    private int ticks;
    private int nextPos;
    private Telemetry telemetry;

    public Lift(double distance, double speed, int nextPos) {

        // Setup variables for use in action
        this.speed = speed;
        this.nextPos = nextPos;

        // Ticks is the encoder ticks used in motion
        this.ticks = (int)(distance * robot.LIFT_COUNTS_PER_ROT);
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);
        this.telemetry = telemetry;

        // Prepare for running to encoder position
        robot.liftMotor.setTargetPosition(robot.liftMotor.getCurrentPosition() + ticks);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public int run() {

        // Set motor power until finished
        robot.liftMotor.setPower(speed);

        if ( !robot.liftMotor.isBusy() ) {
            return nextPos;

        }

        telemetry.addData("Lift:", "Running to: " + ticks +
                ", Running at: " + robot.liftMotor.getCurrentPosition());
        telemetry.update();
        return 0;
    }

    @Override
    public void actionEnd() {

        // Set motor power to 0
        robot.liftMotor.setPower(0);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
