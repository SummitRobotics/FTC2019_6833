package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.main.Hardware;

import java.util.ArrayList;

public abstract class CoreAuto extends LinearOpMode{

    Hardware robot = new Hardware();

    void runPath(ArrayList<CoreAction> path) {

        Integer currentAction = 0;
        Integer nextAction = 0;

        while(nextAction != null) {

            nextAction = 0;

            path.get(currentAction).runInit();

            while (nextAction == 0) {

                nextAction = path.get(currentAction).run();
            }

            if (nextAction == null) {

                break;
            } else {

                currentAction += nextAction;
            }
        }
    }
}