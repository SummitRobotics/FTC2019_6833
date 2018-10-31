package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.Action;

import java.util.ArrayList;

public class DepotSideAuto extends CoreAuto {


    private ArrayList<Action> path;

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");    //
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        path.add(new Action(46.53059, -5.8526087));
        path.add(new Action(45.82741, 11.820012));
        path.add(new Action(0.7, robot.markerDrop));
        path.add(new Action(75.721436, 12.356607));

        runPath(path);

    }
}