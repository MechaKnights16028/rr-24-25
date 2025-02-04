package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "BlueAuto", group = "Autonomous")
public class BlueAuto extends LinearOpMode {

//    public class Lift {
//        private DcMotorEx vlSlides, vlSlides1, vrSlides, vrSlides1;
//        public Lift(HardwareMap hardwareMap) {
//            vlSlides = hardwareMap.get(DcMotorEx.class, "vlSlides");
//            vlSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            vlSlides.setDirection(DcMotorSimple.Direction.FORWARD);
//
//            vlSlides1 = hardwareMap.get(DcMotorEx.class, "vlSlides1");
//            vlSlides1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            vlSlides1.setDirection(DcMotorSimple.Direction.FORWARD);
//
//            vrSlides = hardwareMap.get(DcMotorEx.class, "vrSlides");
//            vrSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            vrSlides.setDirection(DcMotorSimple.Direction.FORWARD);
//
//            vrSlides1 = hardwareMap.get(DcMotorEx.class, "vrSlides1");
//            vrSlides1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            vrSlides1.setDirection(DcMotorSimple.Direction.FORWARD);
//        }
//    }
//    public class Intake {
//        private Servo bar1, bar2;
//
//        public Intake(HardwareMap hardwareMap) {
//            bar1 = hardwareMap.get(Servo.class, "intakeBar1");
//            bar2 = hardwareMap.get(Servo.class, "intakeBar2");
//        }
//    }
//    public class LiftUp implements Action {
//        private boolean initialized = false;
//
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if (!initialized) {
//                vlSlides.setPower(0.8);
//                vlSlides1.setPower(0.8);
//                vrSlides.setPower(0.8);
//                vrSlides1.setPower(0.8);
//                initialzied = true;
//            }
//        }
//    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(9, 63, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose );

        TrajectoryActionBuilder moveToPreload = drive.actionBuilder(initialPose)
                        .lineToY(32);
        waitForStart();
        if (isStopRequested()) return;
        Action trajectoryActionChosen;
        trajectoryActionChosen = moveToPreload.build();
        Actions.runBlocking(
                trajectoryActionChosen
        );
    }
}



