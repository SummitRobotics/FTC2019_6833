package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import org.firstinspires.ftc.teamcode.autonomous.actions.WaitForTime;

import java.util.ArrayList;

@Autonomous(name="DepotSideAuto", group="LinearOpMode")
public class DepotSideAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new MarkerServo(0.5,1));

        path.add(new Lift(3.5, 1, 1));

        path.add(new SampleDetection(1, 10, 17, 10));

        // Left path
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.1, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(30, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-2.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(38, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(0.0,1));
        path.add(new WaitForTime(1, 1));
        path.add(new MoveByEncoder(-1.8, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(80, 0.4, MoveByEncoder.FORWARD, END));

        // Center
        path.add(new MoveByEncoder(50, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(0.0,1));
        path.add(new WaitForTime(1, 1));
        path.add(new MoveByEncoder(-2.0, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(15, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-1.1, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(80, 0.4, MoveByEncoder.FORWARD, END));

        // Right
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-1.1, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(35, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(2.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(25, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(0.0,1));
        path.add(new WaitForTime(1, 1));
        path.add(new MoveByEncoder(-80, -0.2, MoveByEncoder.FORWARD, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}