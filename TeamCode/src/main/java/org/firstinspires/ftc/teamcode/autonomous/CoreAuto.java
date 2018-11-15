package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.main.Hardware;

import java.util.ArrayList;

public abstract class CoreAuto extends LinearOpMode{

    protected Hardware robot = new Hardware();
    protected final Integer END = -1;

    protected void runPath(ArrayList<CoreAction> path) {

        Integer currentAction = 0;
        Integer nextAction = 0;

        do {

            currentAction += nextAction;

            nextAction = 0;

            path.get(currentAction).runInit(hardwareMap, telemetry);

            while (nextAction == 0) {

                nextAction = path.get(currentAction).run();
            }

        } while (!nextAction.equals(END));
    }
}