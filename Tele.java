package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class Tele extends OpMode
{
    public DcMotor FR, FL, BR, BL;
    public Servo SClaw;
    private double powerRY, powerRX, powerLX, powerLY, robotAngle, PowerMultiplier, lf, rb, rf, lb;
    double cpi = 50;

    double cpd = 3.7;
    int inch = 0;

    @Override
    public void init()
    {
        FR = hardwareMap.get(DcMotor.class, "FR");
        FL = hardwareMap.get(DcMotor.class, "FL");
        BR = hardwareMap.get(DcMotor.class, "BR");
        BL = hardwareMap.get(DcMotor.class, "BL");
        SClaw = hardwareMap.get(Servo.class, "SClaw");
        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        FL.setDirection(DcMotorSimple.Direction.FORWARD);



        /*

        BR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        FR.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);

         */



        //test, may remove
        //TopWrist.setDirection(Servo.Direction.REVERSE);


        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        /*

        Slides1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Slides1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Slides2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Slides2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
*/




        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);
        SClaw.setPosition(0.5);


        //elbow1 and elbow2 are continous servos
        //left servo is 0 and right servo is -0.5
    }

    @Override
    public void loop()
    {
        powerLX = gamepad1.left_stick_x;
        powerLY = gamepad1.left_stick_y;
        powerRX = gamepad1.right_stick_x;
        powerRY = gamepad1.right_stick_y;

        robotAngle = Math.atan2(powerLX, powerLY);
        telemetry.addData("Robot angle:", robotAngle);
        telemetry.addData("powerRX: ", gamepad1.right_stick_x);
        telemetry.addData("powerRY: ", gamepad1.right_stick_y);
        telemetry.addData("powerLX: ", gamepad1.left_stick_x);
        telemetry.addData("powerLY: ", gamepad1.left_stick_y);

        telemetry.addData("FR: ", FR.getPower());
        telemetry.addData("FL: ", FL.getPower());
        telemetry.addData("BR: ", BR.getPower());
        telemetry.addData("BL: ", BL.getPower());

        telemetry.addData("Robot angle:", robotAngle);
        telemetry.addData("powerRX: ", gamepad1.right_stick_x);
        telemetry.addData("powerRY: ", gamepad1.right_stick_y);
        telemetry.addData("powerLX: ", gamepad1.left_stick_x);
        telemetry.addData("powerLY: ", gamepad1.left_stick_y);
        telemetry.addData("SClaw:",SClaw.getPosition());


        telemetry.addData("FR: ", FR.getPower());
        telemetry.addData("FL: ", FL.getPower());
        telemetry.addData("BR: ", BR.getPower());
        telemetry.addData("BL: ", BL.getPower());
        telemetry.addData("FR Position: ", FR.getCurrentPosition());
        telemetry.addData("FL Position: ", FL.getCurrentPosition());
        telemetry.addData("BR Position: ", BR.getCurrentPosition());
        telemetry.addData("BL Position: ", BL.getCurrentPosition());

        telemetry.update();

        PowerMultiplier = Math.sqrt((Math.pow(powerLX, 2) + Math.pow(powerLY, 2)));

        lf = (PowerMultiplier*-1*(Math.sin(robotAngle-(Math.PI/4)))) - powerRX;
        rb = (PowerMultiplier*-1*(Math.sin(robotAngle-(Math.PI/4)))) + powerRX;
        lb = (PowerMultiplier*Math.sin(robotAngle+(Math.PI/4))) - powerRX;
        rf = (PowerMultiplier*Math.sin(robotAngle+(Math.PI/4))) + powerRX;

        FR.setPower(rf);
        FL.setPower(lf);

        BR.setPower(rb);
        BL.setPower(lb);
        if(gamepad2.a){
            SClaw.setPosition(0.9);}
        if(gamepad2.y){
            SClaw.setPosition(0.5);}
        }






    // EWrist1 (right) - port 0 of control hub
        // EWrist2 (left) - port 1 of expansion




      /*  if(gamepad2.dpad_up){
            Slides1.setPower(-0.3);
            Slides2.setPower(0.3);
        } */

        /*
        if(gamepad2.b){
            TopWrist.setPosition(TopWrist.getPosition() + 0.08);
        }
        if(gamepad2.x){
            TopWrist.setPosition(TopWrist.getPosition() - 0.08);
        }
/*
     if(gamepad2.x){
         Roller.setPower(0.5);
      }
      if(gamepad2.y){
           Roller.setPower(0);
      }
       if(gamepad2.b){
           Roller.setPower(-1);
      }

 */

        /*

        if(gamepad2.dpad_down){
            Slides1.setPower(-0.55);
            Slides2.setPower(0.55);
        }

        if(gamepad2.dpad_up){
            Slides1.setPower(0.55);
            Slides2.setPower(-0.55);
        }

        if (gamepad2.dpad_down && inch != 0) {
            lineardown(inch, 0.3);
            linearup(0.075, 0.3);
            inch = 0;
        } else if (gamepad2.dpad_up && inch == 0) {
            linearup(5, 0.3);
            inch = 5;
        } else if (gamepad2.dpad_up && inch > 5) {
            lineardown(inch - 5, 0.3);
            inch = 5;
        }

*/

        //slides1: -2150 & 2150

/*
        if (gamepad2.dpad_up) {
            linearup(43, 0.3);
            if(Slides1.getCurrentPosition()<-2149 && Slides2.getCurrentPosition()>2149){
                Slides1.setPower(0);
                Slides2.setPower(0);
            }
        }
        if (gamepad2.dpad_down) {
            linearup(4, -0.3);
        }

 */


/*
        if (gamepad1.x) {
            SClaw.setPosition(0.6);
            linearup(4, 0.3);
            //delay
            lineardown(4,-0.3);

        }


        /*
        if(gamepad2.dpad_down){
            Slides1.setPower(-0.55);
            Slides2.setPower(0.55);
        }

        if(gamepad2.dpad_up){
            Slides1.setPower(0.55);
            Slides2.setPower(-0.55);
        }

         */





    }



    
