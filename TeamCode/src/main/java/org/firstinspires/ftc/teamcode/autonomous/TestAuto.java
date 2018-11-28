package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.IntakeControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import java.util.ArrayList;

@Autonomous(name="TestAuto", group="LinearOpMode")
public class TestAuto extends CoreAuto{

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {

        // Add paths for autonomous
        // Land
        //path.add(new Lift(1, 0.5, 1));

        path.add(new MoveByEncoder(-0.0064930916, 0.7, MoveByEncoder.TURN, 1));
        path.add(new MoveByEncoder(51.18147, 0.7, MoveByEncoder.FORWARD, 1));
        path.add(new MoveByEncoder(1.8099909, 0.7, MoveByEncoder.TURN, 1));

        // Final Move for Left and Center Paths
        path.add(new MoveByEncoder(75, 0.7, MoveByEncoder.FORWARD, 1));
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
