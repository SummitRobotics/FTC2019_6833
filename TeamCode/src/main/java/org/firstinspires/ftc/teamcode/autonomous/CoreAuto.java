package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.main.Hardware;

import java.util.ArrayList;

public abstract class CoreAuto extends LinearOpMode{

    Hardware robot = new Hardware();
    final Integer END = null;

    void runPath(ArrayList<CoreAction> path) {

        int currentAction = 0;
        Integer nextAction = 0;

        do {

            currentAction += nextAction;

            nextAction = 0;

            path.get(currentAction).runInit();

            while (nextAction == 0) {

                nextAction = path.get(currentAction).run();
            }

        } while (nextAction != END);
    }
}