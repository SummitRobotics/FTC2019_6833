package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.CoreAuto;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.IntakeControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import org.firstinspires.ftc.teamcode.autonomous.actions.WaitForTime;

import java.util.ArrayList;

@Autonomous(name="MarkerOnlyAuto", group="LinearOpMode")
public class MarkerOnlyAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new IntakeControl(-1, 1));
        path.add(new MoveByEncoder(60, 0.2, MoveByEncoder.FORWARD, 1));

        path.add(new IntakeControl(0, 1));
        path.add(new MoveByEncoder(-1.8, -0.2, MoveByEncoder.TURN, 1));
        path.add(new MarkerServo(1.0, 1));
        path.add(new WaitForTime(1000, 1));
        path.add(new MoveByEncoder(-10, -0.2, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(0.5, 1));
        path.add(new WaitForTime(1000, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}