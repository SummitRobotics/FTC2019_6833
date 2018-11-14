package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.main.Action;
import java.util.ArrayList;

//Class to run the autonomous for crater side robot placement.
@Autonomous(name="CraterSideAuto", group="Linear Opmode")
public class CraterSideAuto extends CoreAuto {

    private ArrayList<Action> actions = new ArrayList<>();

    public void runOpMode() {

        robot.init(hardwareMap);

        actions.add(0, new Action(Action.Mode.LIFT, 1.3));
        actions.add(1, new Action(Action.Mode.TURN, 20));
        actions.add(2, new Action(Action.Mode.MOVE, 10));
        actions.add(3, new Action(Action.Mode.TURN, -20));
        actions.add(4, new Action(Action.Mode.MOVE, 45));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");
        runActions(actions);
    }
}