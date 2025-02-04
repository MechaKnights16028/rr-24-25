package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ResetEncoders extends DriveCodeCommon{
    @Override
    public void runOpMode(){
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        waitForStart();
        declares();
        resetEncoders(drive);
        while(opModeIsActive()) {
        telemetry(drive);
        }
    }
}
