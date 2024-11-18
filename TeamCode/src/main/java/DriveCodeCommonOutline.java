import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.MecanumDrive;

public class DriveCodeCommonOutline extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    }
    /*
    Hey Alan, sorry I cant be there today so to help below is a general outline so you have something to go off of.
    Side note, The code wasnt pushed to github so I had to make another class.
    * */

    public void Initialization(MecanumDrive drive){
        // I dont think there is a current use for this aside from setting behavior but still good to have since you will have it
        telemetry.addData("Initialized:",true);
        telemetry.update();
        // todo this is the important part.
        // You need to set Zero brake behavior for all motors but most importantly the lifts
        drive.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void drive(MecanumDrive drive){
        // I believe this should be good from friday. If motors dont go in correct direction then reverse them in mecanum drive.
    }
    public void vlift(MecanumDrive drive){
        // Just some bare bones set power for now. We will redo this friday but build team needs this.
        // drive.Lift.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
        // todo also add telemetry for friday
        telemetry.addData("VliftL", /* drive.motor.getCurrentPosition();*/  );
        telemetry.addData("VliftR", /* drive.motor.getCurrentPosition();*/  );
    }
    public void hlift(MecanumDrive drive){
        // same thing but with bumpers and a constant.
        if(gamepad1.left_bumper){
            // negative power idk 0.5 would probably work
        }else if (gamepad1.right_bumper){
            // positive power same as negative.
        }else{
            // power 0
        }
        telemetry.addData("Hlift", /* drive.motor.getCurrentPosition();*/  );
    }
    public void intake(MecanumDrive drive){
        // I cant quite remember the servo set up so youll be mostly on your own for this one.
    if(gamepad1.a){
        // This will be the position for them to pass off
        servo1.setPosition();
        servo2.setPosition();
        servo3.setPosition();
    }else if (gamepad1.b) {
        // Scoring positons
        servo1.setPosition();
        servo2.setPosition();
        servo3.setPosition();
    }else{
        // intake position.
        servo1.setPosition();
        servo2.setPosition();
        servo3.setPosition();
    }
    }
    //todo Alright this should be enough for this practice if you have any extra time look into this.
    // https://docs.ftclib.org/ftclib/features/gamepad-extensions

}
