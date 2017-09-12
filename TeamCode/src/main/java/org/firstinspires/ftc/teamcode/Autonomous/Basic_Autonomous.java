package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoClass;

import static org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity.timeDelay;

/**
 * Created by Thomas on 8/10/2017.
 */

@Autonomous(name = "Basic Movements", group = "Linear OpModes")
public class Basic_Autonomous extends AutoClass {

    /*


    LOTS OF VARIABLES


     */


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initializing");
        telemetry.addData("Notice", "Do NOT click START!");
        telemetry.update();

        init(hardwareMap);

        telemetry.addData("Status: ", "READY");
        telemetry.update();

        waitForStart();

        runtime.reset();

        telemetry.addData("Status: ", "Waiting");
        telemetry.update();

        while (runtime.seconds() < timeDelay) {
            idle();
        }

        telemetry.addData("Status: ", "Running");
        telemetry.update();

        MoveForward(121, 0.75f);

        Thread.sleep(1000);

        TurnLeft(90, 0.25f);

        Thread.sleep(1000);

        MoveForward(125, 0.75f);

        Thread.sleep(1000);

        TurnLeft(30, 0.25f);

        Thread.sleep(1000);

        MoveForward(24, 0.5f);

        Thread.sleep(1000);

        TurnLeft(90, 0.25f);

        Thread.sleep(1000);

        MoveForward(36, 0.5f);

        Thread.sleep(1000);

        TurnRight(90, 0.25f);

        Thread.sleep(1000);

        MoveForward(100, 0.75f);

        Thread.sleep(1000);

        TurnRight(45, 0.25f);

        Thread.sleep(1000);

        MoveForward(36, 0.5f);

        Thread.sleep(1000);


        Thread.sleep(1000);


        Thread.sleep(1000);


        Thread.sleep(1000);


        Thread.sleep(1000);


        Thread.sleep(1000);


        Thread.sleep(1000);

        /*

        DO SOMETHING


         */


    }

}
