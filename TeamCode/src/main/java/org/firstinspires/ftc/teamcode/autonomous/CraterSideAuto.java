package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.Action;

public class CraterSideAuto extends CoreAuto {


    private Action[] path = new Action[4];

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");    //
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        path[0] = new Action(37.061516, -4.2607856);
        path[1] = new Action(65.446976, -12.220759);
        path[2] = new Action(0.7, robot.markerDrop);
        path[3] = new Action(-64.156715, -24.806803);

        runPath(path);

    }
}