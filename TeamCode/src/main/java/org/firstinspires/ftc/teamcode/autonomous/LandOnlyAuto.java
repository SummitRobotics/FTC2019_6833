package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;

import java.util.ArrayList;

/**
 * A simple autonomous that will only land using the mast. This can be used in the case of a broken
 * autonomous or a malfunctioning robot part that prevents the drive train from moving, in which case
 * we will still gain points from landing in autonomous.
 */
@Autonomous(name="LandOnlyAuto", group="LinearOpMode")
public class LandOnlyAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    /**
     * Prepare a simple path to land, then turn away from the latch.
     */
    @Override
    public void runOpMode() {
        // Add paths for autonomous
        path.add(new Lift(3.5, 1, 1));
        path.add(new MoveByEncoder(0.5, 0.2, MoveByEncoder.TURN, END));


        // Update telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        // Run the paths created earlier
        runPath(path);
    }
}