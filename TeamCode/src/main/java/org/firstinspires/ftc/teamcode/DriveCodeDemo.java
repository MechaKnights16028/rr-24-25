package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class DriveCodeDemo extends DriveCodeCommonDemo{

    @Override
    public void runOpMode(){
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        waitForStart();
        declares();
        while(opModeIsActive()) {
            update(drive);
            drives(drive);
            vLift(drive);
            swingbar(drive);
            intakeServos(drive);
            hslides(drive);
            intakeBar(drive);
            head(drive);
            //plane(drive);
        }
    }
}
