package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;

import java.util.ArrayList;

@Autonomous(name="DepotSideAuto", group="LinearOpMode")
public class DepotSideAuto extends CoreAuto {


    private ArrayList<CoreAction> path = new ArrayList<>();

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