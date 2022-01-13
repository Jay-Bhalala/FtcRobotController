package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

/**
 * This class defines all the hardware components of the robot. It has eight DcMotors, five Servos, and one WebcamName. It can reset drive encoders and stop all motors
 */
public class Hardware {

    //Define all the motors, servos, and cameras of the robot

    //Drive Train
    public DcMotor LF, LB, RF, RB;

    //Intake
    public DcMotor intakeMotor;

    //Carousel
    public DcMotor carousel;

    //linear lift
    public DcMotor liftMotor;
    public Servo liftServo;


    /**
     * Creates a new Hardware with all parts connected to a name
     *
     * @param hwmp map of robot parts on the control hub
     */
    public Hardware(HardwareMap hwmp) {

        //Drive Train
        LF = hwmp.dcMotor.get("LF");
        RF = hwmp.dcMotor.get("RF");
        RB = hwmp.dcMotor.get("RB");
        LB = hwmp.dcMotor.get("LB");

        //Intake
        intakeMotor = hwmp.dcMotor.get("intakeMotor");
        liftMotor = hwmp.dcMotor.get("liftMotor");
        carousel = hwmp.dcMotor.get("carousel");

        resetDriveEncoders();

        //Flips motors because they are placed in the opposite direction on the robot---allows for all motors to move in the same direction for one value
        RB.setDirection(DcMotor.Direction.REVERSE);
        RF.setDirection(DcMotor.Direction.REVERSE);

        //Webcam
        //WebcamName = hwmp.get(WebcamName.class, "webcam");
    }

    /**
     * Resets drive encoders so that they are starting from 0 at every time
     * Encoders are used to control how much a motor moves---used for travelling by distance and setting levels for lifting
     */
    public void resetDriveEncoders()
    {
        //Stop and Reset Encoders
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Start motors using resetted encoders
        LF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Stop all the motors and servos
     * Essentially stop the robot
     * Note: Motor power ranges go from -1 to 1 as a double explicit parameter
     * Note: Servo positions go from 0 to 1 as a double explicit parameter. The actual position of 0 and 1 are based on servo limits programmed by the servo programmer
     */
    public void stopAllMotors() {

        //Motors
        LF.setPower(0);
        RF.setPower(0);
        RB.setPower(0);
        LB.setPower(0);
    }

}