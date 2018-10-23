package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.main.BasicHardware;
import org.firstinspires.ftc.teamcode.main.PVector;

@Autonomous(name = "AutoProgram1", group = "Linear OpMode")
public class AutoProgram1 extends CoreAuto {

    BasicHardware robot = new BasicHardware();

    PVector[] moves;

    AutoProgram1() {
        robot.init(hardwareMap);
    }
}
