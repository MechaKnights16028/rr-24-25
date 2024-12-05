package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class DriveCode extends DriveCodeCommon{

    @Override
    public void runOpMode(){
        waitForStart();
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        while(opModeIsActive()) {
            drives(drive);
            vLift(drive);
            hLift(drive);
            intakeServos(drive);
        }
    }
}
