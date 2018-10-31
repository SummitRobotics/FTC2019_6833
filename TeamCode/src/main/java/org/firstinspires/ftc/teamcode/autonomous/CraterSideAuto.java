package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.main.Action;
import java.util.ArrayList;

@Autonomous(name="CraterSideAuto", group="Linear Opmode")
public class CraterSideAuto extends CoreAuto {

    private ArrayList<Action> actions = new ArrayList<Action>();

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");    //
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        actions.add(0, new Action(Action.LIFT, 5));
        actions.add(1, new Action(Action.TURN, -3.149107));
        actions.add(2, new Action(Action.MOVE, 35.678234));
        actions.add(3, new Action(Action.TURN, -11.39856));
        actions.add(4, new Action(Action.MOVE, 69.81121));
        actions.add(5, new Action(Action.MARKER, 0.7));
        actions.add(6, new Action(Action.TURN, 19.630495));
        actions.add(7, new Action(Action.MOVE, 68.385));

        runActions(actions);

    }
}