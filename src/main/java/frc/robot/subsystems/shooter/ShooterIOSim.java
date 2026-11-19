// package frc.robot.subsystems.shooter;

// import static frc.robot.subsystems.shooter.ShooterConstants.*;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.system.plant.DCMotor;
// import edu.wpi.first.math.system.plant.LinearSystemId;
// import edu.wpi.first.wpilibj.simulation.DCMotorSim;

// public class ShooterIOSim implements ShooterIO {
//   private static final DCMotor SHOOTER_GEARBOX = DCMotor.getNEO(1);

//   private final DCMotorSim shooterSim =
//       new DCMotorSim(
//           LinearSystemId.createDCMotorSystem(SHOOTER_GEARBOX, SHOOTER_INERTIA,
// SHOOTER_GEAR_RATIO),
//           SHOOTER_GEARBOX);
//   private final PIDController pid = new PIDController(SHOOTER_KP, SHOOTER_KI, SHOOTER_KD);

//   private boolean closedLoop = false;
//   private double setpointRadPerSec = 0.0;
//   private double appliedVolts = 0.0;

//   @Override
//   public void updateInputs(ShooterIOInputs inputs) {
//     if (closedLoop) {
//       appliedVolts =
//           SHOOTER_KV * setpointRadPerSec
//               + pid.calculate(shooterSim.getAngularVelocityRadPerSec(), setpointRadPerSec);
//     }
//     appliedVolts = MathUtil.clamp(appliedVolts, -12.0, 12.0);

//     shooterSim.setInputVoltage(appliedVolts);
//     shooterSim.update(0.02);

//     inputs.shooterVelocityRadPerSec = shooterSim.getAngularVelocityRadPerSec();
//     inputs.shooterPositionRad = shooterSim.getAngularPositionRad();
//     inputs.shooterAppliedVolts = appliedVolts;
//     inputs.shooterCurrentAmps = Math.abs(shooterSim.getCurrentDrawAmps());
//     inputs.shooterClosedLoop = closedLoop;
//   }

//   @Override
//   public void setShooterVelocity(double velocityRadPerSec) {
//     closedLoop = true;
//     setpointRadPerSec = velocityRadPerSec;
//   }

//   @Override
//   public void setShooterVoltage(double volts) {
//     closedLoop = false;
//     appliedVolts = volts;
//   }
// }
