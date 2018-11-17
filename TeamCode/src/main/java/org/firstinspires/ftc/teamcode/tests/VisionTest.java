package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.CoreAuto;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;

import java.util.ArrayList;

@Autonomous(name="VisionTest", group="LinearOpMode")
public class VisionTest extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {

        //Initializes hardware
        robot.init(hardwareMap);

        path.add(new SampleDetection(1, 3, 2));

        // Left
        path.add(new MoveByEncoder(30,0.4, robot.TURN, 2));

        // Right
        path.add(new MoveByEncoder(-30,0.4, robot.TURN, 1));

        // Center
        path.add(new MoveByEncoder(30, 0.4, robot.FORWARD, END));


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        runPath(path);
    }
}