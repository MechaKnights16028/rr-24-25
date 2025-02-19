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
import com.acmerobotics.roadrunner.Trajectory;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Config
@Autonomous(name = "BlueAuto", group = "Autonomous")
public class BlueAuto extends LinearOpMode {

    public class IntakeBar{
        private Servo intakeBar1, intakeBar2;
        public IntakeBar(HardwareMap hardwareMap) {
            intakeBar1 = hardwareMap.get(Servo.class, "barRight");
            intakeBar2 = hardwareMap.get(Servo.class, "barLeft");
        }
        public class BarScore implements Action{
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeBar1.setPosition(0.0);
                intakeBar2.setPosition(1.0);
                return false;
            }
        }
        public Action barScore(){
            return new BarScore();
        }
        public class BarPickup implements Action{
            public boolean run(@NonNull TelemetryPacket packet) {
                intakeBar1.setPosition(1.0);
                intakeBar2.setPosition(0.0);
                return false;
            }
        }
        public Action barPickup(){
            return new BarPickup();
        }
    }
    public class Lift {
        private DcMotorEx vlSlides, vlSlides1, vrSlides, vrSlides1;
        double liftPower = 0.75;

        public Lift(HardwareMap hardwareMap) {
            vlSlides = hardwareMap.get(DcMotorEx.class, "leftLift");
            vlSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vlSlides.setDirection(DcMotorSimple.Direction.FORWARD);
            vlSlides1 = hardwareMap.get(DcMotorEx.class, "leftLift1");
            vlSlides1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vlSlides1.setDirection(DcMotorSimple.Direction.FORWARD);


            vrSlides = hardwareMap.get(DcMotorEx.class, "rightLift");
            vrSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vrSlides.setDirection(DcMotorSimple.Direction.FORWARD);
            vrSlides1 = hardwareMap.get(DcMotorEx.class, "rightLift1");
            vrSlides1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            vrSlides1.setDirection(DcMotorSimple.Direction.FORWARD);



        }
        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    vlSlides.setPower(liftPower);
                    vrSlides.setPower(liftPower);
                    vlSlides1.setPower(-liftPower);
                    vrSlides1.setPower(-liftPower);
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
                    vlSlides1.setPower(0);
                    vrSlides1.setPower(0);
                    return false;
                }
            }
        }
        public Action liftUp() {
            return new LiftUp();
        }

        public class LiftUpPartial implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    vlSlides.setPower(liftPower);
                    vrSlides.setPower(liftPower);
                    vlSlides1.setPower(-liftPower);
                    vrSlides1.setPower(-liftPower);
                    initialized = true;
                }

                double pos = vlSlides.getCurrentPosition();
                telemetry.addData("lift",pos);
                telemetry.update();
                packet.put("liftPos", pos);
                if (pos > -1000.0) {
                    return true;
                } else {
                    vlSlides.setPower(0);
                    vrSlides.setPower(0);
                    vlSlides1.setPower(0);
                    vrSlides1.setPower(0);
                    return false;
                }
            }
        }
        public Action liftUpPartial() {
            return new LiftUpPartial();
        }
        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                telemetry.addData("entered","liftdown");
                telemetry.update();
                if (!initialized) {
                    vlSlides.setPower(-liftPower);
                    vrSlides.setPower(-liftPower);
                    vlSlides1.setPower(liftPower);
                    vrSlides1.setPower(liftPower);
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
                    vrSlides1.setPower(0);
                    vlSlides1.setPower(0);
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
                    vlSlides.setPower(liftPower);
                    vrSlides.setPower(liftPower);
                    vlSlides1.setPower(-liftPower);
                    vrSlides1.setPower(-liftPower);
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
                    vrSlides1.setPower(0);
                    vlSlides1.setPower(0);
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
        Pose2d initialPose = new Pose2d(0, 63, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose );
        Lift lift = new Lift(hardwareMap);

        IntakeBar intakeBar = new IntakeBar(hardwareMap);

        Action moveToPreload = drive.actionBuilder(initialPose)
                        .lineToY(28)
                        .build();
        Action scorePreload = drive.actionBuilder(new Pose2d(0,28,Math.toRadians(90)))
                        .lineToY(35)
                        .build();
        Action moveBack = drive.actionBuilder(new Pose2d(0,35,Math.toRadians(90)))
                        .lineToY(50)
                        .build();
        Action park = drive.actionBuilder(new Pose2d(0,50,Math.toRadians(90)))
                        .turn(Math.toRadians(-90))
                        .lineToX(-60)
                        .build();
        Action moveRight = drive.actionBuilder(new Pose2d(0,35,Math.toRadians(90)))
                        .strafeTo(new Vector2d(-35,35))
                        .build();
        Action moveForward = drive.actionBuilder(new Pose2d(-35,35,Math.toRadians(90)))
                        .strafeTo(new Vector2d(-45, 6))
                        .build();
        Action bringBlock1 = drive.actionBuilder(new Pose2d(-35,6,Math.toRadians(90)))
                        .strafeTo(new Vector2d(-47,6))
                        .strafeTo(new Vector2d(-47,60))
                        .build();
        Action getBlock2 = drive.actionBuilder(new Pose2d(-45,60,Math.toRadians(90)))
                        .strafeTo(new Vector2d(-47,15))
                        .splineToLinearHeading(new Pose2d(-60,55,Math.toRadians(90)),0.5)
                        .build();
        Action getSpecimen1 = drive.actionBuilder(new Pose2d(-60,55,Math.toRadians(90)))
                        .strafeTo(new Vector2d(-45,45))
                        .strafeTo(new Vector2d(-45,55))
                        .build();
        Action scoreSpecimen1 = drive.actionBuilder(new Pose2d(-45,60,Math.toRadians(90)))
                        .splineToLinearHeading(new Pose2d(3,33,Math.toRadians(90)),1)
                        .strafeTo(new Vector2d(3,32))
                        .build();
        //Action scoreSpecimen2 =  drive.actionBuilder(new Pose2d())

        waitForStart();
        if (isStopRequested()) return;
        Actions.runBlocking(
                new SequentialAction(
                        lift.liftInit(),
                        moveToPreload,
                        lift.liftUp(),
                        lift.liftDown(),
                        scorePreload,
                        moveRight,
                        //moveForward,
                        bringBlock1,
                        getBlock2,
                        intakeBar.barPickup(),
                        getSpecimen1,
                        intakeBar.barScore(),
                        scoreSpecimen1,
                        lift.liftUpPartial(),
                        intakeBar.barScore(),
                        lift.liftUp(),
                        lift.liftDown()


                        //trajectory4
                        //trajectory3
                )
        );
    }
}