package org.firstinspires.ftc.teamcode.autonomous;

import org.firstinspires.ftc.teamcode.main.PVector;

//733.9539, -733.9539
//2482.1912
//
//695.76056, -695.76056
//1915.5548
//
//-61.434097, 61.434097
//4454.3467

public class MainAuto extends CoreAuto {


    public PVector[] path = new PVector[3];

    public void runOpMode() {

        robot.init(hardwareMap);
        path[0] = new PVector(2482, 734);
        path[1] = new PVector(1915, 696);
        path[2] = new PVector(4454, -61);

        encoderLift(1.0, 5);

        runPath(path);

    }
}
