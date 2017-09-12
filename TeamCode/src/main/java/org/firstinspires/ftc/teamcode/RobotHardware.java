package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Thomas on 8/10/2017.
 */

public class RobotHardware {

    public DcMotor
            leftDrive = null,
            rightDrive = null;


    HardwareMap hwmap = null;

    private static double revMotorCount = 287;
    private static double gearReductionRatio = 1;
    private static double wheelDiameter = 4;
    public double countsPerInch = ((revMotorCount * gearReductionRatio) / (wheelDiameter * Math.PI));

    public RobotHardware() {
    }

    public void init(HardwareMap ahwmap) {

        hwmap = ahwmap;

        leftDrive = hwmap.dcMotor.get("left_motor");
        rightDrive = hwmap.dcMotor.get("right_motor");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);

    }

    public void ResetDriveMotors() {

        StopDriveMotors();

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void StopAllMotors() {

        StopDriveMotors();

    }

    public void StopDriveMotors() {

        leftDrive.setPower(0);
        rightDrive.setPower(0);

    }


}
