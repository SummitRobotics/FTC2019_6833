package org.firstinspires.ftc.teamcode.autonomous.actions;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

// Interface to create new action classes
public interface CoreAction {

    Hardware robot = new Hardware();

    /**
     * Method to run once at start of action use
     * @param hardwareMap For using hardware within actions
     * @param telemetry For updating telemetry within actions
     */
    void actionInit(HardwareMap hardwareMap, Telemetry telemetry);

    /**
     * Method to be called until action is complete
     * @return Returns 0 if action is incomplete, returns distance between current index and next
     * index when action is complete
     */
    int run();

    /**
     * Method to run once at the end of action use
     */
    void actionEnd();
}