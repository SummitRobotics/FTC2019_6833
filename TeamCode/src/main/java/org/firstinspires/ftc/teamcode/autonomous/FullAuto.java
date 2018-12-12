package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.IntakeControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import org.firstinspires.ftc.teamcode.autonomous.actions.WaitForTime;

import java.util.ArrayList;

@Autonomous(name="FullAuto", group="LinearOpMode")
public class FullAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new Lift(3.5, 1, 1));

        path.add(new SampleDetection(1, 8, 13, 8));

        // Left path
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.1, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(30, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-2.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(35, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(80, 0.4, MoveByEncoder.FORWARD, END));

        // Center
        path.add(new MoveByEncoder(45, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-2.5, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(25, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-0.8, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(80, 0.4, MoveByEncoder.FORWARD, END));

        // Right
        path.add(new MoveByEncoder(10, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-1.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(30, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(2.2, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(20, 0.4, MoveByEncoder.FORWARD, 1));
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