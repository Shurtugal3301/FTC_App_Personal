package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotHardware;

/**
 * Created by Thomas on 8/15/2017.
 */

public class AutoClass extends LinearOpMode {

    //region Enums

    private enum MoveDir {

        FORWARD, BACKWARD

    }

    private enum TurnDir {

        LEFT, RIGHT

    }

    //endregion

    public RobotHardware robot = new RobotHardware();
    public ElapsedTime runtime = new ElapsedTime();

    private int leftDriveTarget;
    private int rightDriveTarget;

    public AutoClass() {
    }

    @Override
    public void runOpMode() throws InterruptedException {
    }

    public void init(HardwareMap hwMap) {

        robot.init(hwMap);

        robot.ResetDriveMotors();

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    //region Turning

    private void Turn(TurnDir dir, double deg, float speed) {

        int _deg = (int) ((22 * Math.PI) / (360 / deg));

        int _target = -_deg * (int) robot.countsPerInch;

        double leftDriveSpeed = 0;
        double rightDriveSpeed = 0;

        char turnDir = 'x';

        switch (dir) {

            case LEFT:
                leftDriveSpeed = 0.1;
                rightDriveSpeed = -speed;
                turnDir = 'l';
                break;

            case RIGHT:
                leftDriveSpeed = -speed;
                rightDriveSpeed = 0.1;
                turnDir = 'r';
                break;

        }

        switch (turnDir) {

            case 'r':
                leftDriveTarget = _target + robot.leftDrive.getCurrentPosition();
                break;

            case 'l':
                rightDriveTarget = _target + robot.rightDrive.getCurrentPosition();
                break;

        }

        robot.leftDrive.setPower(leftDriveSpeed);
        robot.rightDrive.setPower(rightDriveSpeed);

        while ((Math.abs(leftDriveTarget - robot.leftDrive.getCurrentPosition()) > 3 && turnDir == 'r')
                || (Math.abs(rightDriveTarget - robot.rightDrive.getCurrentPosition()) > 3 && turnDir == 'l')) {

            // Display it for the driver.
            telemetry.addData("Path1", "Running to %7d :%7d", leftDriveTarget, rightDriveTarget);
            telemetry.addData("Path2", "Running at %7d :%7d",
                    robot.leftDrive.getCurrentPosition(),
                    robot.rightDrive.getCurrentPosition());
            telemetry.update();

            idle();

        }

        robot.StopDriveMotors();

    }

    public void TurnLeft(double deg, float speed) {

        Turn(TurnDir.LEFT, deg, speed);

    }

    public void TurnRight(double deg, float speed) {

        Turn(TurnDir.RIGHT, deg, speed);

    }

    //endregion

    //region Moving

    private void Move(MoveDir dir, double dist, float speed) {

        int _dir = 0;

        switch (dir) {

            case FORWARD:
                _dir = -1;
                break;

            case BACKWARD:
                _dir = 1;
                break;

        }

        int _target = ((int) (robot.countsPerInch * dist)) * _dir;

        leftDriveTarget = _target + robot.leftDrive.getCurrentPosition();
        rightDriveTarget = _target + robot.rightDrive.getCurrentPosition();

        robot.leftDrive.setPower(speed * (float) _dir);
        robot.rightDrive.setPower(speed * (float) _dir);

        while (Math.abs(leftDriveTarget - robot.leftDrive.getCurrentPosition()) > 3
                && Math.abs(rightDriveTarget - robot.rightDrive.getCurrentPosition()) > 3) {

            // Display it for the driver.
            telemetry.addData("Path1", "Running to %7d :%7d", leftDriveTarget, rightDriveTarget);
            telemetry.addData("Path2", "Running at %7d :%7d",
                    robot.leftDrive.getCurrentPosition(),
                    robot.rightDrive.getCurrentPosition());
            telemetry.update();

            idle();

        }

        robot.StopDriveMotors();

    }

    public void MoveForward(double dist, float speed) {

        Move(MoveDir.FORWARD, dist, speed);

    }

    public void MoveBackward(double dist, float speed) {

        Move(MoveDir.BACKWARD, dist, speed);

    }

    //endregion

}
