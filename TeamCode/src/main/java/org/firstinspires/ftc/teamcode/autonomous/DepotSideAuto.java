package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;
import org.firstinspires.ftc.teamcode.autonomous.actions.Lift;
import java.util.ArrayList;

@Autonomous(name="DepotSideAuto", group="LinearOpMode")
public class DepotSideAuto extends CoreAuto {

    //Initializes action list
    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() {

        //Initializes hardware
        robot.init(hardwareMap);

        path.add(new Lift(1.3, 0.4, 1));
        path.add(new MoveByEncoder(70,0.4, robot.FORWARD, END));


        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Running");

        runPath(path);
    }
}