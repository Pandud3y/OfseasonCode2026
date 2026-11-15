package frc.robot.subsystems.shooter;

import static frc.robot.subsystems.shooter.ShooterConstants.*;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;

public class ShooterIOReal implements ShooterIO {
  private final SparkMax shooterMotor = new SparkMax(SHOOTER_MOTOR_ID, MotorType.kBrushless);
  private final RelativeEncoder shooterEncoder = shooterMotor.getEncoder();
  private final PIDController pid = new PIDController(SHOOTER_KP, SHOOTER_KI, SHOOTER_KD);

  private boolean closedLoop = false;
  private double setpointRadPerSec = 0.0;

  public ShooterIOReal() {
    var config = new SparkMaxConfig();
    // coast so the flywheel spins down instead of braking hard
    config.idleMode(IdleMode.kCoast).smartCurrentLimit(40).inverted(false);
    shooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void updateInputs(ShooterIOInputs inputs) {
    double velocityRadPerSec =
        Units.rotationsPerMinuteToRadiansPerSecond(shooterEncoder.getVelocity())
            / SHOOTER_GEAR_RATIO;

    if (closedLoop) {
      double volts =
          SHOOTER_KV * setpointRadPerSec + pid.calculate(velocityRadPerSec, setpointRadPerSec);
      shooterMotor.setVoltage(MathUtil.clamp(volts, -12.0, 12.0));
    }

    inputs.shooterVelocityRadPerSec = velocityRadPerSec;
    inputs.shooterPositionRad =
        Units.rotationsToRadians(shooterEncoder.getPosition()) / SHOOTER_GEAR_RATIO;
    inputs.shooterAppliedVolts = shooterMotor.getAppliedOutput() * shooterMotor.getBusVoltage();
    inputs.shooterCurrentAmps = shooterMotor.getOutputCurrent();
    inputs.shooterClosedLoop = closedLoop;
  }

  @Override
  public void setShooterVelocity(double velocityRadPerSec) {
    closedLoop = true;
    setpointRadPerSec = velocityRadPerSec;
  }

  @Override
  public void setShooterVoltage(double volts) {
    closedLoop = false;
    shooterMotor.setVoltage(MathUtil.clamp(volts, -12.0, 12.0));
  }
}
