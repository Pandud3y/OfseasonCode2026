package frc.robot.subsystems.intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeIOReal implements IntakeIO {
  private final TalonFX intakeMotor;
  private final TalonFXConfiguration intakeMotorConfiguration;

  public IntakeIOReal(TalonFX intakeMotor) {
    this.intakeMotor = intakeMotor;
    this.intakeMotorConfiguration = new TalonFXConfiguration();
  }
    
  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    inputs.intakeConnected = intakeMotor.isConnected();
    inputs.intakePositionRad = intakeMotor.getPosition().getValueAsDouble();
    inputs.intakeVelocityRadPerSec = intakeMotor.getVelocity().getValueAsDouble();
    inputs.intakeAppliedVolts = intakeMotor.getMotorVoltage().getValueAsDouble();
    inputs.intakeCurrentAmps = intakeMotor.getSupplyCurrent().getValueAsDouble();
  }

  @Override
  public void setintakeVelocity(double velocityRadPerSec) {
    intakeMotor.setControl(new com.ctre.phoenix6.controls.VelocityVoltage(velocityRadPerSec));
  }

  @Override
  public void setIntakeVoltage(double voltage) {
    intakeMotor.setVoltage(voltage);
  }
}
