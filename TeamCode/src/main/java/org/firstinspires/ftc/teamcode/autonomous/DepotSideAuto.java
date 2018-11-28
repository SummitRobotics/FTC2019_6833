package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.IntakeControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import java.util.ArrayList;

@Autonomous(name="DepotSideAuto", group="LinearOpMode")
public class DepotSideAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {

        // Add paths for autonomous
        // Land
        //path.add(new Lift(1, 0.5, 1));

        // Identify gold mineral and select path
        path.add(new IntakeControl(0.9, 1));

        path.add(new SampleDetection(1, 6, 10, 6));

        // Left Path
        path.add(new MoveByEncoder(-0.62, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(37, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.35,0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(25, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(1, 1));
        path.add(new MoveByEncoder(1.6, 0.4, MoveByEncoder.TURN, 4));

        // Center Path
        path.add(new MoveByEncoder(51.18147, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(1,1));
        path.add(new MoveByEncoder(2, 0.4, MoveByEncoder.TURN, 1));

        // Final Move for Left and Center Paths
        path.add(new MoveByEncoder(75, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new IntakeControl(0, END));

        // Right Path
        path.add(new MoveByEncoder(0.3759613, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(40.731678, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-0.7808528, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(20.248219, 0.4, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(1,1));
        path.add(new MoveByEncoder(-0.4619584, 0.4, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(-69.319824, 0.4, MoveByEncoder.FORWARD, 1));

        path.add(new IntakeControl(0, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}