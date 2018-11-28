package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.IntakeControl;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.SampleDetection;
import java.util.ArrayList;

@Autonomous(name="VisionOnlyAuto", group="LinearOpMode")
public class VisionOnlyAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {

        // Add paths for autonomous
        // Land
        //path.add(new Lift(1, 0.5, 1));

        path.add(new SampleDetection(1, 3, 2, 3));

        // Left Path
        path.add(new MoveByEncoder(0.62, 0.4, MoveByEncoder.TURN, 2));

        // Right Path
        path.add(new MoveByEncoder(-0.62, -0.4, MoveByEncoder.TURN, 1));

        // Center and End
        path.add(new MoveByEncoder(37, 0.4, MoveByEncoder.FORWARD, END));

        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}