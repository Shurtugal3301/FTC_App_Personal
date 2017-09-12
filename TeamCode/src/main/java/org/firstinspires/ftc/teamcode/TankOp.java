package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Thomas on 8/12/2017.
 */

@TeleOp(name = "Tank Op", group = "Linear Opmodes")
public class TankOp extends OpMode {

    //region Enums

    private enum driveMode {

        FAST, SLOW

    }

    //endregion

    //region Variables

    private RobotHardware robot = new RobotHardware();
    private ElapsedTime runTime = new ElapsedTime();

    private driveMode currentDrive = driveMode.FAST;

    private float maxDriveSpeed = 0.75f;

    private boolean changingDriveMode = false;

    //endregion

    @Override
    public void init() {

        robot.init(hardwareMap);

        telemetry.addData("Status: ", "Initializing");
        telemetry.update();

        robot.ResetDriveMotors();

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        changingDriveMode = false;
        currentDrive = driveMode.FAST;

        telemetry.addData("Status: ", "READY");
        telemetry.update();

    }

    @Override
    public void loop() {

        FeedBack();

        Drive();

    }

    @Override
    public void stop() {

        robot.StopAllMotors();

        telemetry.addData("Status: ", "TankOp Complete");
        telemetry.update();

    }

    private void FeedBack() {

        telemetry.addData("Status", "Running");
        telemetry.addData("Time", (int) runTime.seconds());
        telemetry.addData("Drive Mode", currentDrive);
        telemetry.addData("Left Drive", robot.leftDrive.getCurrentPosition());
        telemetry.addData("Right Drive", robot.rightDrive.getCurrentPosition());
        telemetry.update();

    }

    private void Drive() {

        UpdateDriveSpeed();

        if (currentDrive == driveMode.FAST) {

            maxDriveSpeed = 0.8f;

        } else if (currentDrive == driveMode.SLOW) {

            maxDriveSpeed = 0.4f;

        } else {

            telemetry.addData("ERROR: ", "No Drive Mode Specified");
            telemetry.update();

        }

        ManualDrive();

    }

    private void ManualDrive() {

        robot.leftDrive.setPower((double) (gamepad1.left_stick_y * maxDriveSpeed));
        robot.rightDrive.setPower((double) (gamepad1.right_stick_y * maxDriveSpeed));

    }

    private void UpdateDriveSpeed() {

        if (gamepad1.right_bumper && !changingDriveMode) {

            currentDrive = driveMode.FAST;
            changingDriveMode = true;

            robot.StopDriveMotors();

        }
        if (gamepad1.left_bumper && !changingDriveMode) {

            currentDrive = driveMode.SLOW;
            changingDriveMode = true;

            robot.StopDriveMotors();

        }
        if (!gamepad1.start && !gamepad1.back) {

            changingDriveMode = false;

        }

    }


}
