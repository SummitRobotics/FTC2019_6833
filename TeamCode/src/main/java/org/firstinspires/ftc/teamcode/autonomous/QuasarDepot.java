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
 * An autonomous program to be used when working with team Robotics Quasar Alliance to prevent our
 * robots from colliding during autonomous. The robot will run a path similar to that of the DepotSideAuto
 * , however, it will wait 10 seconds first to prevent collision with another team.
 */
@Autonomous(name="QuasarDepot", group="LinearOpMode")
public class QuasarDepot extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    /**
     * Prepare paths to wait, then run a normal autonomous.
     */
    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new MarkerServo(0.5,1));

        path.add(new Lift(3.5, 1, 1));

        path.add(new SampleDetection(1, 9, 14, 9));

        // Left path
        path.add(new WaitForTime(7, 1));
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.1, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(30, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-2.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(38, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(0.0,1));
        path.add(new WaitForTime(1, END));

        // Center
        path.add(new WaitForTime(7, 1));
        path.add(new WaitForTime(10, 1));
        path.add(new MoveByEncoder(50, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(0.0,1));
        path.add(new WaitForTime(1, END));

        // Right
        path.add(new WaitForTime(7, 1));
        path.add(new WaitForTime(10, 1));
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-1.1, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(35, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(2.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(25, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(0.0,1));
        path.add(new WaitForTime(1, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}