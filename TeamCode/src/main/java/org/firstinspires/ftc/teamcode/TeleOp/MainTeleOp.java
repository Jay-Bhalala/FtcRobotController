package org.firstinspires.ftc.teamcode.TeleOp;

//import android.os.Handler;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
import org.firstinspires.ftc.teamcode.Hardware;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import java.lang.Math;

/*
  Notes:
    Encoders are doubles
    .getCurrentPosition() retrieves encoder values
    left and right triggers on controllers are scaled 0-1
    .getMode() exists
 */
@TeleOp(name = "Main TeleOp", group = "Linear OpMode")

/**
  MainTeleOp is the class responsible for all of the TeleOp methods. It has a robot, movement, rotation, strafe, eight motors, and five servos
 */
public class MainTeleOp extends OpMode {

    //Create a robot---responsible for connecting hardware of Hardware class to methods
    Hardware robot;

    //Directions
    double movement;
    double rotation;
    double strafe;

    //Define the Motors and Servos here to not rely on referencing the robot variable to access the motors and servos
    DcMotor LF, LB, RF, RB, intakeMotor, liftMotor, carousel;
    Servo liftServo;


    @Override
    /**
     * Initializes the robot by mapping the hardware, resetting encoders, and setting servos to the correct starting positions
     */
    public void init() {
        //Map hardware
        robot = new Hardware(hardwareMap);

        //Assign the motors and servos to the ones on the robot to not require calling robot everytime a method or servo needs to be called.
        //Motors for drive train
        LF = robot.LF;
        RF = robot.RF;
        RB = robot.RB;
        LB = robot.LB;

        //Motors and servos for functionalities
        intakeMotor = robot.intakeMotor;
        liftMotor = robot.liftMotor;
        carousel = robot.carousel;

    }

    @Override
    /**
     * Runs the main methods of TeleOp and telemetry.
     * Loop repeats so that it is checking controllers and telemetry values at all times for continuous running
     */
    public void loop() {
        //Methods responsible for control of different parts of the the robot

        Intake();
        DriveControl();
        LinearLift();
        Carousel();
        
//
//        if(pushServo.getPosition() == Servo.MIN_POSITION)
//        {
//            pushServo.setPosition(Servo.MAX_POSITION);
//        }
//
//        if(gamepad1.x)
//        {
//            telemetry.addData("Wobble Goal Arm Position: ", wobbleGoalArm.getCurrentPosition());
//        }
//        //Show Telemetry on Driver Station Phone
//        telemetry.update();
    }
    
    //Triggers control movement of carousel
    public void Carousel() {
        //Right trigger movers carousel clockwise
        if(gamepad1.right_trigger > 0)
        {
            carousel.setPower(1);
        }
        //Left trigger moves carousel counter-clockwise
        else if (gamepad1.left_trigger > 0)
        {
            carousel.setPower(-1);
        }
        //Carousel does not move without triggers
        else
        {
            carousel.setPower(0);
        }
    }
    
    public void LinearLift() {
        //Turns on HD Hex Motor for Linear Lift
        if(gamepad1.y){
            liftMotor.setPower(1);
            //need to test these values
            //add code where servo is moved up slightly
        }
        else if(gamepad1.a){
            liftMotor.setPower(-1);
            //need to test these values
            //add code where servo is moved up slightly
        }
        else
        {
            liftMotor.setPower(0);
        }
//        if(gamepad1.y){
//            //add servo code where it flips the box over to drop the freight
//        }
         
    }
    
    public void Intake() {
        //Turns on intake and shooting
        if(gamepad1.b){
            intakeMotor.setPower(-1);
        }
        if(gamepad1.x){
            intakeMotor.setPower(1);
        }
        else
        {
            intakeMotor.setPower(0);
        }
    }
    

    public void DriveControl() {
        movement = gamepad1.left_stick_y;
        rotation = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;

        double magnitude = Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2));
        double direction = Math.atan2(-gamepad1.left_stick_x, gamepad1.left_stick_y);
        boolean precision = gamepad1.right_bumper;

        //INFO Increasing speed to a maximum of 1
        double lf = magnitude * Math.sin(direction + Math.PI / 4) - rotation;
        double lb = magnitude * Math.cos(direction + Math.PI / 4) - rotation;
        double rf = magnitude * Math.cos(direction + Math.PI / 4) + rotation;
        double rb = magnitude * Math.sin(direction + Math.PI / 4) + rotation;

        double hypot = Math.hypot(movement, strafe);
        double ratio;
        if (movement == 0 && strafe == 0)
            ratio = 1;
        else if(precision)
            ratio = hypot / (Math.max(Math.max(Math.max(Math.abs(lf), Math.abs(lb)), Math.abs(rb)), Math.abs(rf))) / 2;
        else
            ratio = hypot / (Math.max(Math.max(Math.max(Math.abs(lf), Math.abs(lb)), Math.abs(rb)), Math.abs(rf)));

        LF.setPower(ratio * lf);
        LB.setPower(ratio * lb);
        RF.setPower(ratio * rf);
        RB.setPower(ratio * rb);
    }
}