package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.main.Hardware;

import java.util.ArrayList;

@Autonomous(name="mainAuto", group="Linear Opmode")
public abstract class CoreAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();
    Hardware robot = new Hardware();

    void runPath(ArrayList<CoreAction> path) {

        int nextAction = 0;
        int currentAction = 0;

        while (opModeIsActive()) {

            if (currentAction == -1) {

                break;
            }

            path.get(nextAction).runInit();

            while (nextAction == currentAction) {

                nextAction = path.get(nextAction).run();
            }

            currentAction = nextAction;
        }
    }
}
