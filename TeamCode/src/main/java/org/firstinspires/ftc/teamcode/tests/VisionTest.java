package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.CoreAuto;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import java.util.ArrayList;

@Autonomous(name="VisionTest", group="LinearOpMode")
public class VisionTest extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        //Initialize robot hardware
        robot.init(hardwareMap);

        // Add paths and sample detections
        path.add(new SampleDetection(1, 3, 2));

        // Left
        path.add(new MoveByEncoder(30,0.4, robot.TURN, 2));
        // Right
        path.add(new MoveByEncoder(-30,0.4, robot.TURN, 1));
        // Center
        path.add(new MoveByEncoder(30, 0.4, robot.FORWARD, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        // Update telemetry
        telemetry.addData("Status", "Running");
        telemetry.update();

        // Run the path created earlier
        runPath(path);
    }
}