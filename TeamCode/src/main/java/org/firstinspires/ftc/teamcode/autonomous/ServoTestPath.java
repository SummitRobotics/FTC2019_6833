package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.autonomous.actions.CoreAction;
import org.firstinspires.ftc.teamcode.autonomous.actions.MarkerServo;
import org.firstinspires.ftc.teamcode.autonomous.actions.MoveByEncoder;

import java.util.ArrayList;

@Autonomous(name="ServoTestPath", group="LinearOpMode")
public class ServoTestPath extends CoreAuto {

    private ArrayList<CoreAction> path = new ArrayList<>();

    @Override
    public void runOpMode() throws InterruptedException {

        path.add(new MoveByEncoder(20, 0.2, MoveByEncoder.FORWARD, 1));
        path.add(new MarkerServo(.25,1));
        path.add(new MoveByEncoder(1, 0.2, MoveByEncoder.TURN, END));

        runPath(path);
    }
}
