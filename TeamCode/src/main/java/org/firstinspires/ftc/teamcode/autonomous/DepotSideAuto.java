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

        // Create actions
        actions.add(0, new Action(Action.Mode.LIFT, -1));
        actions.add(1, new Action(Action.Mode.MOVE, 65.80335));
        actions.add(2, new Action(Action.Mode.MARKER, 0.5));
        actions.add(3, new Action(Action.Mode.TURN, 15.088611));
        actions.add(4, new Action(Action.Mode.MOVE, 40.444588));
        actions.add(5, new Action(Action.Mode.TURN, 1.7310498));
        actions.add(6, new Action(Action.Mode.MOVE, 37.905727));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");
        runActions(actions);

    }
}