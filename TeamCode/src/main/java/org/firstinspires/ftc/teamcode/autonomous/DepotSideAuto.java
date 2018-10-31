package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;

import java.util.ArrayList;

public class DepotSideAuto extends CoreAuto {


    private ArrayList<CoreAction> path;

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        path.add(new MoveByEncoder(3,3,3,1));
        path.add(new MoveByEncoder(4,4,4, -1));


        runPath(path);

    }
}