package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="drive code",group = "Linear OpMode")
public class DriveCode extends DriveCodeCommon{
    public void RunOpMode(){
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        waitForStart();
        drives(drive);
        vLift(drive);
        hLift(drive);
        intakeServos(drive);

    }
}
