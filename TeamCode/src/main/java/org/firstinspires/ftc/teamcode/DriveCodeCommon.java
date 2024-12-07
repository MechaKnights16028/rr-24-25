package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

@Config
public class DriveCodeCommon extends LinearOpMode {
    public static double intakeBarZero = 0.75;
    public static double intakeServoZero = 0.25;
    public static double intakeBarHandoff = 0.2;
    public static double intakeBarPickUp = 1.0;
    public static double intakePosOne = 0.75;
    public static double clawPosOne = 0.0;
    public static double clawPosTwo = 0.5;
    GamepadEx gamepadEx;
    ToggleButtonReader clawToggle;
    ToggleButtonReader handoffToggle;
    ToggleButtonReader intakeToggle;
    ToggleButtonReader intakeBarIntakeToggle;
    @Override
    public void runOpMode() throws InterruptedException{
    }
    public void declares(){
         gamepadEx = new GamepadEx(gamepad2);
         clawToggle = new ToggleButtonReader(gamepadEx, GamepadKeys.Button.X);
         handoffToggle = new ToggleButtonReader(gamepadEx, GamepadKeys.Button.Y);
         intakeToggle = new ToggleButtonReader(gamepadEx, GamepadKeys.Button.A);
         intakeBarIntakeToggle = new ToggleButtonReader(gamepadEx, GamepadKeys.Button.B);
    }
    public void drives(MecanumDrive drive){
        drive.leftBack.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
        drive.leftFront.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
        drive.rightBack.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
        drive.rightFront.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
    }
    public void update(MecanumDrive drive){
        clawToggle.readValue();
        intakeToggle.readValue();
        intakeBarIntakeToggle.readValue();
        handoffToggle.readValue();
    }
    public void vLift(MecanumDrive drive){
        drive.vlSlides.setPower(gamepad2.left_trigger-gamepad2.right_trigger);
        drive.vrSlides.setPower(-(gamepad2.left_trigger-gamepad2.right_trigger));
    }
    public void hLift(MecanumDrive drive){
        if(gamepad2.dpad_left) {
            drive.hSlides.setPower(1);
        }else if(gamepad2.dpad_right) {
            drive.hSlides.setPower(-1);
        }else{
            drive.hSlides.setPower(0);
        }
    }
    public void intakeServos(MecanumDrive drive){
        /*if (gamepad2.a){
            drive.intakeBar.setPosition(intakeBarZero);
        }
        else if (false){
            drive.intakeBar.setPosition(0.4);
            telemetry.addData("nothing pressed", false);
        }
        else if (gamepad2.right_bumper){
            drive.intakeBar.setPosition(0.8);
            telemetry.addData("nothing pressed", false);
        } else if (gamepad2.left_bumper) {
            drive.intakeBar.setPosition(0.2);
            telemetry.addData("nothing pressed", false);
        } else if (gamepad2.x){
            drive.intake.setPosition(0.5);
            telemetry.addData("nothing pressed", false);
        }
        else if(gamepadEx.getButton(GamepadKeys.Button.B)){
            drive.intake.setPosition(intakeServoZero);
            telemetry.addData("nothing pressed", false);
        }
        else{
            telemetry.addData("nothing pressed", true);
        }*/
        if(clawToggle.getState()){
            drive.claw.setPosition(clawPosTwo);
        }
        else{
            drive.claw.setPosition(clawPosOne);
        }
        if(handoffToggle.getState()){
            drive.intakeBar.setPosition(intakeBarHandoff);
        }
        else if(intakeBarIntakeToggle.getState()){
            drive.intakeBar.setPosition(intakeBarPickUp);
        }
        else{
            drive.intakeBar.setPosition(intakeBarZero);
        }
        if(intakeToggle.getState()){
            drive.intake.setPosition(intakePosOne);
        }
        else {
            drive.intake.setPosition(intakeServoZero);
        }
        telemetry.update();
    }
}
