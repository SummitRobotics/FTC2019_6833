package org.firstinspires.ftc.teamcode.autonomous.actions;

import org.firstinspires.ftc.teamcode.autonomous.CoreAuto;
import org.firstinspires.ftc.teamcode.main.Hardware;

public interface CoreAction {

    Hardware robot = new Hardware();

    void runInit();

    boolean run();
}