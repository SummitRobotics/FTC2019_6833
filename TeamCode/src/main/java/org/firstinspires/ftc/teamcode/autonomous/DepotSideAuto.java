package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;

import java.util.ArrayList;

@Autonomous(name="DepotSideAuto", group="LinearOpMode")
public class DepotSideAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {

        //Initializes hardware
        robot.init(hardwareMap);

        path.add(new MoveByEncoder(30,0.4, robot.FORWARD,2));
        path.add(new MoveByEncoder(10,.1,robot.FORWARD,END));
        path.add(new MoveByEncoder(30,-.45, robot.TURN, END));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        runPath(path);
    }
}