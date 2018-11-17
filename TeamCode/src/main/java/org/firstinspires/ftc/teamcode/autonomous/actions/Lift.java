package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Lift implements CoreAction {

    private double speed;
    int ticks;
    Integer nextPos;

    public Lift(double distance, double speed, Integer nextPos) {

        this.speed = speed;
        this.nextPos = nextPos;

        this.ticks = (int)(distance * robot.LIFT_COUNTS_PER_ROT);
    }

    @Override
    public void runInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);

        robot.liftMotor.setTargetPosition(robot.leftDrive.getCurrentPosition() + ticks);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public int run() {

        robot.liftMotor.setPower(speed);

        if ( !robot.liftMotor.isBusy() ) {

            killRobot();

            return nextPos;

        }

        return 0;
    }

    private void killRobot() {

        robot.liftMotor.setPower(0);
        robot.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
