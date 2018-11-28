package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeControl implements CoreAction {

    double speed;
    int nextPos;

    public IntakeControl(double speed, int nextPos) {

        this.speed = speed;
        this.nextPos= nextPos;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);
    }

    @Override
    public int run() {

        robot.intakeMotor.setPower(speed);

        return nextPos;
    }

    @Override
    public void actionEnd() {

    }
}
