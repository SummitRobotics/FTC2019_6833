package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MarkerServo extends CoreAction {

    double position;
    int nextPos;
    Telemetry telemetry;

    public MarkerServo(double position, int nextPos) {

        this.position = position;
        this.nextPos = nextPos;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

        robot.init(hardwareMap);
        this.telemetry = telemetry;
    }

    @Override
    public int run() {

        robot.markerDrop.setPosition(position);
        telemetry.addData("Current Position", robot.markerDrop.getPosition());
        telemetry.addData("Set Position", position);

        return 0;
    }

    @Override
    public void actionEnd() {

    }
}
