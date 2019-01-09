package org.firstinspires.ftc.teamcode.autonomous.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.main.Hardware;

/**
 * An abstract class used to create new actions. All actions will extend this class. This prepares
 * the telemetry and robot variables, and creates three abstract methods to be used in each action.
 */
public abstract class CoreAction {

    Hardware robot;
    public int nextPos = -1;

    /**
     * Method to run once at start of action use.
     * @param robot The robot to send action to.
     * @param telemetry For updating telemetry within actions.
     */
    public abstract void actionInit(Hardware robot, Telemetry telemetry);

    /**
     * Method to be called until action is complete
     * @return Returns 0 if action is incomplete, returns distance between current index and next
     * index when action is complete
     */
    public abstract int run();

    /**
     * Method to run once at the end of action use
     */
    public abstract void actionEnd();
}