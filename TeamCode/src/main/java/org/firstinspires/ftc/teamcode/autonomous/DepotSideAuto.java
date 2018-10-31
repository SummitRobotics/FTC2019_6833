package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.main.Action;

@Autonomous(name="DepotSideAuto", group="Linear Opmode")
public class DepotSideAuto extends CoreAuto {


    private Action[] path = new Action[4];

    public void runOpMode() {

        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");    //
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        liftMast(0.7, 3);
        path[0] = new Action(46.53059, -5.8526087);
        path[1] = new Action(45.82741, 11.820012);
        path[2] = new Action(0.7, robot.markerDrop);
        path[3] = new Action(75.721436, 12.356607);

        runPath(path);

    }
}