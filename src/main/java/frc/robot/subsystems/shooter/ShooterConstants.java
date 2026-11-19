package frc.robot.subsystems.shooter;

public class ShooterConstants {
  // PLACEHOLDER VALUES, NEED TO FIND ACTUAL VALUES
  public static final int SHOOTER_MOTOR_ID = 0;
  public static final double SHOOTER_GEAR_RATIO = 1.0;
  public static final double SHOOTER_INERTIA = 0.004; // kg*m^2

  // velocity closed loop gains (volts per rad/s of error)
  public static final double SHOOTER_KP = 0.05;
  public static final double SHOOTER_KI = 0.0;
  public static final double SHOOTER_KD = 0.0;
  // feedforward: volts per rad/s
  public static final double SHOOTER_KV = 0.02;
  public static final double SHOOTER_KS = 0.0;
  public static final double SHOOTER_KA = 0.0;

  public static final double SHOOTER_VELOCITY = 0.0; // rad/s
  public static final double SHOOTER_ACCELERATION = 0.0; // rad/s^2
}
