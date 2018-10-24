package org.firstinspires.ftc.teamcode.autonomous;

public class MainAuto extends CoreAuto {

    public void runOpMode() {

        robot.init(hardwareMap);
        encoderLift(1.0, 5);


    }
}
