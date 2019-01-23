package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeControl extends CoreAction {

    int direction;

    public IntakeControl(int direction, int nextPos) {

        this.direction = direction;
        this.nextPos = nextPos;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {
        robot.init(hardwareMap);
    }

    @Override
    public int run() {
        if (direction == -1) {
            robot.intakeServo.setDirection(Servo.Direction.REVERSE);
        } else if (direction == 1) {
            robot.intakeServo.setDirection(Servo.Direction.FORWARD);
        } else {
            robot.intakeServo.setPosition(0.5);
        }

        return nextPos;
    }

    @Override
    public void actionEnd() {

    }
}
