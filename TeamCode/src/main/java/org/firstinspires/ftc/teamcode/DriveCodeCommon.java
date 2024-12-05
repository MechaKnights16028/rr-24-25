package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class DriveCodeCommon extends LinearOpMode {
    public double intakeBarZero = 0.0;
    public double intakeServoZero = 0.25;
    @Override
    public void runOpMode() throws InterruptedException{
    }
    public void drives(MecanumDrive drive){
        drive.leftBack.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
        drive.leftFront.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
        drive.rightBack.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
        drive.rightFront.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
    }
    public void vLift(MecanumDrive drive){
        drive.vlSlides.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
        drive.vrSlides.setPower(gamepad1.left_trigger-gamepad1.right_trigger);
    }
    public void hLift(MecanumDrive drive){
        if(gamepad1.dpad_left) {
            drive.hSlides.setPower(1);
        }else if(gamepad1.dpad_right) {
            drive.hSlides.setPower(-1);
            drive.intakeBar.setPosition(intakeBarZero);
            drive.intake.setPosition(intakeServoZero);
        }else{
            drive.hSlides.setPower(0);
        }
    }
    public void intakeServos(MecanumDrive drive){
        if (gamepad1.a){
            drive.intakeBar.setPosition(intakeBarZero);
        }
        else if (gamepad1.y){
            drive.intakeBar.setPosition(0.4);
        }
        else if (gamepad1.right_bumper){
            drive.intakeBar.setPosition(0.8);
        } else if (gamepad1.left_bumper) {
            drive.intakeBar.setPosition(1.2);
        } else if (gamepad1.x){
            drive.intake.setPosition(0.5);
        }
        else if(gamepad1.b){
            drive.intake.setPosition(intakeServoZero);
        }
    }
}
