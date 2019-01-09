package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import org.firstinspires.ftc.teamcode.autonomous.actions.WaitForTime;

import java.util.ArrayList;

/**
 * The autonomous to be used when the robot is on the depot side of the field. A program that
 * will land, use the phone camera to scan for minerals, drive over the gold mineral, drop the team
 * marker in the depot, then drive into the crater, then move into the crater.
 */
@Autonomous(name="DepotSideAuto", group="LinearOpMode")
public class DepotSideAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    /**
     * Prepare paths to land, use the phone camera to scan for minerals, drive over the gold mineral,
     * drop the team marker in the depot, then drive into the crater, then move into the crater.
     */
    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new MarkerServo(0.5,1));

        //path.add(new Lift(3.5, 1, 1));

        path.add(new SampleDetection(1, 8, 11, 8));

        // Left Path
        path.add(new MoveByEncoder(4.5, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(0.9, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(26.5, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(0.9, 0.4, MoveByEncoder.LEFT, 1));
        path.add(new MoveByEncoder(34, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.59, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(-80, -0.4, MoveByEncoder.FORWARD, END));

        // Center path
        path.add(new MoveByEncoder(57, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(-80, 0.4, MoveByEncoder.FORWARD, END));

        // Right Path
        path.add(new MoveByEncoder(4.5, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-0.9, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(26.5, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(0.9, 0.4, MoveByEncoder.RIGHT, 1));
        path.add(new MoveByEncoder(34, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-80, -0.4, MoveByEncoder.FORWARD, END));



        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}