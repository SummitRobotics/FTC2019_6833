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

        actions.add(0, new Action(Action.Mode.LIFT, 1.0));
        actions.add(1, new Action(Action.Mode.TURN, -3.0416064));
        actions.add(2, new Action(Action.Mode.MOVE, 34.630424));
        actions.add(3, new Action(Action.Mode.TURN, -11.2911415));
        actions.add(4, new Action(Action.Mode.MOVE, 71.78856));
        actions.add(5, new Action(Action.Mode.MARKER, 0.7));
        actions.add(6, new Action(Action.Mode.TURN, 19.525671));
        actions.add(7, new Action(Action.Mode.MOVE, 69.56635));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");
        runActions(actions);
    }
}