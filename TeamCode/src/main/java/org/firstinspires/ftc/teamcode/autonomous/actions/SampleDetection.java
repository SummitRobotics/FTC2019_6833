package org.firstinspires.ftc.teamcode.autonomous.actions;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.main.Hardware;

/**
 * The SampleDetection class extends the CoreAction abstract class and represents a use of the camera
 * to view minerals on the robot.
 */
public class SampleDetection extends CoreAction {

    // Variables for action
    private int nextPos1, nextPos2, nextPos3;
    private Telemetry telemetry;

    // Variables for Vuforia and Tensor Flow
    private static final String VUFORIA_KEY = "AT1C2Ar/////AAABmfar9R+GH0MImzCa8UF4lUZ8LgjTo9mRlnRVKWMQiuxY/ZtbqSk4lG/4py3aMDqB0hP1FGC9EzgF2wmcVWVtl3hyTngl17UAeBJaROQuvCuBI4BX+PzqZGuvU50uzGRpNAmRs+dbUtOlxgQTn7CAhvMvVPSu30KbWGAVxjS9FoWIm76KsTmO4sJ16usgbwgb+Y6Rj2NMhgYa3Dd+z9z8nIt5dvay8M4/XgtBNfEEf4eH+w0Mbs9tQGc9pSAe3VG7m/T69LmD1Thmd641EflttJ4geP7TbI9q7e2LPhGwLLE+1FaHeaSa2OdCbzFcITVhHtop+F1qNWJbKWpWKHwUEbeAf2do9lTWV0PjoUTcE44y";
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    /**
     * Constructs a new instance of the SampleDetection class with unique parameters describing the length and type
     * of action to be preformed.
     * @param nextPos1 The amount of positions the CoreAuto class should move to find the next action if the mineral is on the left side.
     * @param nextPos2 The amount of positions the CoreAuto class should move to find the next action if the mineral is on the center side.
     * @param nextPos3 The amount of positions the CoreAuto class should move to find the next action if the mineral is on the right side.
     * @param defaultPos The amount of positions the CoreAuto class should move to find the next action if the detection is unsuccessful.
     */
    public SampleDetection(int nextPos1, int nextPos2, int nextPos3, int defaultPos) {

        this.nextPos1 = nextPos1;
        this.nextPos2 = nextPos2;
        this.nextPos3 = nextPos3;
        this.nextPos = defaultPos;
    }

    /**
     * Initializes the new action: Prepares tensorflow object.
     * @param robot The robot to send action to.
     * @param telemetry For updating telemetry within actions.
     */
    @Override
    public void actionInit(Hardware robot, Telemetry telemetry) {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        this.telemetry = telemetry;
        this.robot = robot;

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
            telemetry.update();
        }

        tfod.activate();
    }

    /**
     * Runs the SampleDetection action until complete: Check if three minerals are detected and send the position of the gold mineral.
     * @return Returns 0 if action is incomplete, returns distance between current index and next
     * index when action is complete
     */
    @Override
    public int run() {
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                telemetry.update();

                // Check if 3 minerals have been detected
                if (updatedRecognitions.size() == 3) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;

                    // Get mineral positions
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }

                    // Complete actions based on mineral positions
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {

                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Side: ", "Left");
                            telemetry.update();
                            return nextPos1; //Left
                        } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Side: ", "Right");
                            telemetry.update();
                            return nextPos3; //Right
                        } else {
                            telemetry.addData("Side: ", "Center");
                            telemetry.update();
                            return nextPos2; //Center
                        }
                    }
                }
            }
        }

        // Return 0 if action is still in progress
        return 0;
    }

    /**
     * Ends the action: Shutdown tensorflow.
     */
    @Override
    public void actionEnd() {
        // Kill camera
        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = robot.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", robot.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
