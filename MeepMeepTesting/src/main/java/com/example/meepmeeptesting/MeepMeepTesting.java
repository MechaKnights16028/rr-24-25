package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 63, Math.toRadians(90)))
                .lineToY(28)
                .lineToY(35)
                .strafeTo(new Vector2d(-35,35))
                .strafeTo(new Vector2d(-35,6))
                .strafeTo(new Vector2d(-45,6))
                .strafeTo(new Vector2d(-45,60))
                .strafeTo(new Vector2d(-47,15))
                .splineToLinearHeading(new Pose2d(-60,55,Math.toRadians(90)),0.5)
                .strafeTo(new Vector2d(-45,45))
                .strafeTo(new Vector2d(-45,60))
                .splineToLinearHeading(new Pose2d(3,33,Math.toRadians(90)),1)
                .strafeTo(new Vector2d(3,30))
                //.splineToLinearHeading(new Pose2d(-50,60,Math.toRadians(90)),5)
                //.turn(Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}