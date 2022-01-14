package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Hardware;

public class autonomous extends LinearOpMode {
    Hardware robot;

    DcMotor LF, LB, RF, RB, intakeMotor, liftMotor, carousel;
    Servo liftServo;

    @Override
    public void runOpMode() {
        robot = new Hardware(hardwareMap);

        // assigning motors
        LF = robot.LF;
        RF = robot.RF;
        RB = robot.RB;
        LB = robot.LB;

        //Motors for intake
    }

}
