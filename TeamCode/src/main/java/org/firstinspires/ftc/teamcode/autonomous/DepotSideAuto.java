package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.main.Action;
import java.util.ArrayList;

// Class to run the autonomous for depot side robot placement
@Autonomous(name="DepotSideAuto", group="Linear Opmode")
public class DepotSideAuto extends CoreAuto {

    private ArrayList<Action> actions = new ArrayList<Action>();

    public void runOpMode() {

        robot.init(hardwareMap);

        actions.add(0, new Action(Action.Mode.LIFT, 1));
        actions.add(1, new Action(Action.Mode.TURN, -4.9401503));
        actions.add(2, new Action(Action.Mode.MOVE, 46.30991));
        actions.add(3, new Action(Action.Mode.TURN, 10.079811));
        actions.add(4, new Action(Action.Mode.MOVE, 44.180626));
        actions.add(5, new Action(Action.Mode.MARKER, 0.7));
        actions.add(6, new Action(Action.Mode.TURN, 11.021976));
        actions.add(7, new Action(Action.Mode.MOVE, 79.63415));

        telemetry.addData("Status", "Initialized");    //
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");
        runActions(actions);

    }
}