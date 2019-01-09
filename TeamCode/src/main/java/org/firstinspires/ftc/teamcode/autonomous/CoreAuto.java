package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.WaitForTime;
import org.firstinspires.ftc.teamcode.main.Hardware;

import java.util.ArrayList;

/**
 * CoreAuto is an abstract class that is used to create new autonomous programs and run through their
 * actions. A list of actions will be run through using the runPath method within this class.
 */
abstract class CoreAuto extends LinearOpMode{

    final int END = -1;
    /**
     * The instance of Hardware to be used in all actions.
     */
    private Hardware robot = new Hardware();

    /**
     * The runPath method will run through all paths in an autonomous program. Each path will be
     * initialized, then run through until completion.
     * @param path The list of paths to be run through.
     */
    void runPath(ArrayList<CoreAction> path) {

        robot.init(hardwareMap);

        // variables to store the currently running action location in the list of paths and the
        // location of the next path to run.
        int currentAction = 0;
        int nextAction = 0;
        ElapsedTime runtime = new ElapsedTime();

        // Loop through every action until next action == END (-1)
        do {

            // Increase current action by next action. Next action represents the distance between
            // two actions
            currentAction += nextAction;
            nextAction = 0;

            // Initialize current action
            path.get(currentAction).actionInit(robot, telemetry);

            // Continue looping through run method until action completes and doesn't return 0
            runtime.reset();
            while (nextAction == 0 && opModeIsActive()) {

                nextAction = path.get(currentAction).run();

                // End the program if runtime exceeds 5 seconds
                if (runtime.seconds() > 5 && !(path.get(currentAction) instanceof WaitForTime)) {
                    telemetry.addData("Failed","Next Position: " + path.get(currentAction).nextPos);
                    telemetry.update();
                    nextAction = path.get(currentAction).nextPos;
                }
            }

            // End current actions
            path.get(currentAction).actionEnd();

        } while (nextAction != END && opModeIsActive());

        // Stop all motion.
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        robot.liftMotor.setPower(0);
    }
}