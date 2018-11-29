package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.IntakeControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import org.firstinspires.ftc.teamcode.autonomous.actions.WaitForTime;

import java.util.ArrayList;

@Autonomous(name="VisionMarkerAuto", group="LinearOpMode")
public class VisionMarkerAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new SampleDetection(1, 3, 2, 3));

        // Left Path
        path.add(new MoveByEncoder(0.62, 0.2, MoveByEncoder.TURN, 2));

        // Right Path
        path.add(new MoveByEncoder(-0.62, -0.2, MoveByEncoder.TURN, 1));

        // Center and End
        path.add(new IntakeControl(-1, 1));
        path.add(new MoveByEncoder(37, 0.2, MoveByEncoder.FORWARD, 1));

        path.add(new IntakeControl(0, 1));
        path.add(new MoveByEncoder(-10, -0.2, MoveByEncoder.FORWARD, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}
