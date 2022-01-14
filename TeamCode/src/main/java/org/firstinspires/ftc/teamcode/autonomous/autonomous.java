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
    public void runOpMode() throws InterruptedException{
        //Map the hardware for robot
        robot = new Hardware(hardwareMap);

        // assigning motors
        LF = robot.LF;
        RF = robot.RF;
        RB = robot.RB;
        LB = robot.LB;

        //Motors for intake
        intakeMotor = robot.intakeMotor;
        carousel = robot.carousel;
        liftMotor = robot.liftMotor;
        liftServo = robot.liftServo;

        waitForStart();

        //Spins the carousel
        carousel.setPower(1);
        sleep(1000);

        //backwards
        LF.setPower(.75);
        RF.setPower(.75);
        LB.setPower(.75);
        RB.setPower(.75);
        sleep(300);

        //strafe right
        LF.setPower(-.5);
        RF.setPower(.5);
        LB.setPower(.5);
        RB.setPower(-.5);
        sleep(250);

        //turn right
        LF.setPower(.5);
        RF.setPower(-.5);
        LB.setPower(.5);
        RB.setPower(-.5);
        sleep(750);

        //drive forward toward fright
        LF.setPower(1);
        RF.setPower(1);
        LB.setPower(1);
        RB.setPower(1);
        sleep(3000);

        //intake
        intakeMotor.setPower(1);
        sleep(1000);

        //turn 180 degrees
        LF.setPower(.5);
        RF.setPower(-.5);
        LB.setPower(.5);
        RB.setPower(-.5);
        sleep(1500);

        //move forward again
        LF.setPower(1);
        RF.setPower(1);
        LB.setPower(1);
        RB.setPower(1);
        sleep(2000);

        //turn left
        LF.setPower(-.5);
        RF.setPower(.5);
        LB.setPower(-.5);
        RB.setPower(.5);
        sleep(750);

        //move forward if needed

        //linear lift dump onto hub
        liftMotor.setPower(1);
        sleep(1000);
        liftServo.setPosition(1);
        sleep(1000);
        liftMotor.setPower(-1);
        sleep(1000);

        //turn right
        LF.setPower(.5);
        RF.setPower(-.5);
        LB.setPower(.5);
        RB.setPower(-.5);
        sleep(750);

        //adjust strafe if needed

        //move forward
        LF.setPower(1);
        RF.setPower(1);
        LB.setPower(1);
        RB.setPower(1);
        sleep(1500);

        telemetry.addData("path", "complete");
        telemetry.update();
        sleep(1000);

    }

}
