package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

public interface CoreAction {

    Hardware robot = new Hardware();

    void runInit(HardwareMap hardwareMap, Telemetry telemetry);

    Integer run();
}