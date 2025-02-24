package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.SequentialAction;
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
@Autonomous(name = "RedAuto", group = "Autonomous")
public class RedAuto extends LinearOpMode {

    public class Lift {
        private DcMotorEx vlSlides, vlSlides1, vrSlides, vrSlides1;
        public Lift(HardwareMap hardwareMap) {
            vlSlides = hardwareMap.get(DcMotorEx.class, "leftLift");
            vlSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vlSlides.setDirection(DcMotorSimple.Direction.FORWARD);


            vrSlides = hardwareMap.get(DcMotorEx.class, "rightLift");
            vrSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vrSlides.setDirection(DcMotorSimple.Direction.FORWARD);


        }
        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    vlSlides.setPower(1);
                    vrSlides.setPower(1);
                    initialized = true;
                }

                double pos = vlSlides.getCurrentPosition();
                telemetry.addData("lift",pos);
                telemetry.update();
                packet.put("liftPos", pos);
                if (pos > -1800.0) {
                    return true;
                } else {
                    vlSlides.setPower(0);
                    vrSlides.setPower(0);
                    return false;
                }
            }
        }
        public Action liftUp() {
            return new LiftUp();
        }

        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                telemetry.addData("entered","liftdown");
                telemetry.update();
                if (!initialized) {
                    vlSlides.setPower(-1);
                    vrSlides.setPower(-1);
                    initialized = true;
                }

                double pos = vlSlides.getCurrentPosition();
                telemetry.addData("lift",pos);
                telemetry.update();
                packet.put("liftPos", pos);
                if (pos < 0) {
                    return true;
                } else {
                    telemetry.addData("exited","liftdown");
                    telemetry.update();
                    vrSlides.setPower(0);
                    vlSlides.setPower(0);
                    return false;
                }
            }
        }
        public Action liftDown(){
            return new LiftDown();
        }

        public class LiftInit implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    vlSlides.setPower(1);
                    vrSlides.setPower(1);
                    initialized = true;
                }

                double pos = vlSlides.getCurrentPosition();
                telemetry.addData("lift",pos);
                telemetry.update();
                packet.put("liftPos", pos);
                if (pos > -700) {
                    return true;
                } else {
                    vrSlides.setPower(0);
                    vlSlides.setPower(0);
                    return false;
                }
            }
        }
        public Action liftInit(){
            return new LiftInit();
        }
    }
    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, -63, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose );
        Lift lift = new Lift(hardwareMap);

        TrajectoryActionBuilder moveToPreload = drive.actionBuilder(initialPose)
                .lineToY(-28);
        TrajectoryActionBuilder scorePreload = drive.actionBuilder(initialPose)
                .lineToY(-35);
        TrajectoryActionBuilder moveBack = drive.actionBuilder(initialPose)
                .lineToY(-50);
        TrajectoryActionBuilder park = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(90))
                .lineToX(60);
        waitForStart();
        if (isStopRequested()) return;
        Action trajectory1,trajectory2, trajectory3, trajectory4;
        trajectory1 = moveToPreload.build();
        trajectory2 = scorePreload.build();
        trajectory3 = park.build();
        trajectory4 = moveBack.build();
        Actions.runBlocking(
                new SequentialAction(
                        lift.liftInit(),
                        trajectory1,
                        lift.liftUp(),
                        //trajectory2,
                        lift.liftDown(),
                        trajectory4,
                        trajectory3
                )
        );
    }
}