package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
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
        path.add(new Lift(1.3, 0.5, 1));

        // Identify gold mineral and select path
        path.add(new SampleDetection(1, 6, 10));

        // Left Path
        path.add(new MoveByEncoder(-0.5477395, 0.7, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(38.929222, 0.7, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.3171391, 0.7, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(29.37876, 0.7, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.210834, 0.7, MoveByEncoder.TURN, 4));

        // Center Path
        path.add(new MoveByEncoder(-0.0064930916, 0.7, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(51.18147, 0.7, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.8099909, 0.7, MoveByEncoder.TURN, 1));

        // Final Move for Left and Center Paths
        path.add(new MoveByEncoder(75, 0.7, MoveByEncoder.FORWARD, END));

        // Right Path
        path.add(new MoveByEncoder(0.3759613, 0.7, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(40.731678, 0.7, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-0.7808528, 0.7, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(20.248219, 0.7, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(-0.4619584, 0.7, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(-69.319824, 0.7, MoveByEncoder.FORWARD, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}