package frc.robot.subsystems.shooter;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;

public class ShooterIOReal implements ShooterIO {
  private final SparkMax shooterMotor =
      new SparkMax(ShooterConstants.SHOOTER_MOTOR_ID, MotorType.kBrushless);
  private final RelativeEncoder shooterEncoder = shooterMotor.getEncoder();
  private final PIDController pid =
      new PIDController(
          ShooterConstants.SHOOTER_KP, ShooterConstants.SHOOTER_KI, ShooterConstants.SHOOTER_KD);

  // true = we are trying to hold a speed, false = we are just giving it volts
  private boolean usingPID = false;
  private double targetSpeed = 0.0; // radians per second

  public ShooterIOReal() {
    var config = new SparkMaxConfig();
    config.idleMode(IdleMode.kCoast); // coast so the flywheel slows down on its own
    config.smartCurrentLimit(40);
    config.inverted(false); // change to true if it spins the wrong way
    shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    // the encoder gives us RPM, so turn it into radians per second
    double speed = shooterEncoder.getVelocity() * 2 * Math.PI / 60;
    speed = speed / ShooterConstants.SHOOTER_GEAR_RATIO;

    // the encoder gives us rotations, so turn it into radians
    double position = shooterEncoder.getPosition() * 2 * Math.PI;
    position = position / ShooterConstants.SHOOTER_GEAR_RATIO;

    if (usingPID) {
      double volts = ShooterConstants.SHOOTER_KV * targetSpeed + pid.calculate(speed, targetSpeed);
      shooterMotor.setVoltage(volts);
    }

    inputs.shooterVelocityRadPerSec = speed;
    inputs.shooterPositionRad = position;
    inputs.shooterAppliedVolts = shooterMotor.getAppliedOutput() * shooterMotor.getBusVoltage();
    inputs.shooterCurrentAmps = shooterMotor.getOutputCurrent();
    inputs.shooterClosedLoop = usingPID;
  }

  @Override
  public void setShooterVelocity(double velocityRadPerSec) {
    usingPID = true;
    targetSpeed = velocityRadPerSec;
  }

  @Override
  public void setShooterVoltage(double volts) {
    usingPID = false;
    shooterMotor.setVoltage(volts);
  }
}
