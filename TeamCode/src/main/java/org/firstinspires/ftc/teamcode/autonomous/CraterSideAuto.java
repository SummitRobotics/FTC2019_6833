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
 * The autonomous to be used when the robot is on the crater side of the field. A simple program that
 * will land, use the phone camera to scan for minerals, drive over the gold mineral, then drive into
 * the crater.
 */
@Autonomous(name="CraterSideAuto", group="LinearOpMode")
public class CraterSideAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    /**
     * Prepare paths to land, use the phone camera to scan for minerals, go over the gold
     * mineral, then drive into the crater.
     */
    @Override
    public void runOpMode() {
        // Add paths for autonomous
        telemetry.addData("Ss", "awefawefd");
        telemetry.update();
        path.add(new MarkerServo(0.5,1));

        path.add(new Lift(3.5, 1, 1));

        path.add(new SampleDetection(1, 4, 5, 4));

        // Left path
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.1, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(40, 0.4, MoveByEncoder.FORWARD, END));

        // Center
        path.add(new MoveByEncoder(50, 0.4, MoveByEncoder.FORWARD, END));

        // Right
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-1.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(40, 0.4, MoveByEncoder.FORWARD, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}