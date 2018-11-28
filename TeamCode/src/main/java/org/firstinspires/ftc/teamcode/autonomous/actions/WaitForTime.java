package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WaitForTime extends CoreAction {

    double time;
    int nextPos;

    public WaitForTime(double time, int nextPos) throws InterruptedException {

        this.time = time;
        this.nextPos = nextPos;
    }

    @Override
    public void actionInit(HardwareMap hardwareMap, Telemetry telemetry) {

    }

    @Override
    public int run() throws InterruptedException {

        wait((long) time);

        return nextPos;
    }

    @Override
    public void actionEnd() {

    }
}
