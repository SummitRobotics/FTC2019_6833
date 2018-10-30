package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.main.Action;

//-3.981292
//47.59374
//
//9.95072
//22.325
//
//7.088131
//21.58165
//
//4.661365
//37.130745
//
//4.7618604
//20.821053
//
//-9.134702
//28.87537

public class MainAuto extends CoreAuto {


    public Action[] path = new Action[7];

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");    //
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        path[0] = new Action(-3.981292, 47.59374);
        path[1] = new Action(9.95072, 22.325);
        path[2] = new Action(0.7, robot.markerDrop);
        path[3] = new Action(7.088131, 21.58165);
        path[4] = new Action(4.661365, 37.130745);
        path[5] = new Action(4.7618604, 20.821053);
        path[6] = new Action(-9.134702, 28.87537);

        runPath(path);

    }
}