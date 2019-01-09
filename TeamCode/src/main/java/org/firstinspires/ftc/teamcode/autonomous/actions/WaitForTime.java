package org.firstinspires.ftc.teamcode.autonomous.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * The WaitForTime class extends the CoreAction abstract class and represents a wait action in the robot.
 */
public class WaitForTime extends CoreAction {

    private long time;
    private ElapsedTime runtime = new ElapsedTime();

    /**
     * Constructs a new instance of the WaitForTime class with unique parameters describing the length and type
     * of action to be preformed.
     * @param time The amount of time to wait.
     * @param nextPos The amount of positions the CoreAuto class should move to find the next action.
     */
    public WaitForTime(double time, int nextPos) {

        this.time = (long) time;
        this.nextPos = nextPos;
    }

    /**
     * Initializes the new action: Resets the current runtime.
     * @param robot The robot to send action to.
     * @param telemetry For updating telemetry within actions.
     */
    @Override
    public void actionInit(Hardware robot, Telemetry telemetry) {
        runtime.reset();
    }

    /**
     * Runs the WaitForTime action until complete: Check if runtime has exceeded the target time.
     * @return Returns 0 if action is incomplete, returns distance between current index and next
     * index when action is complete
     */
    @Override
    public int run() {

        if (runtime.seconds() > time) {
            return nextPos;
        }

        return 0;
    }

    /**
     * Ends the action.
     */
    @Override
    public void actionEnd() {}
}
