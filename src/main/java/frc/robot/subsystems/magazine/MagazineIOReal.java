package frc.robot.subsystems.magazine;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class MagazineIOReal implements MagazineIO {
  private final Talon magazineMotor;
  private final DigitalInput upLimitSwitch;
  private final DigitalInput downLimitSwitch;

  public MagazineIOReal() {
    magazineMotor = new Talon(0); // Replace 0 with actual port
    upLimitSwitch = new DigitalInput(0); // Replace 0 with actual port
    downLimitSwitch = new DigitalInput(0); // Replace 0 with actual port
  }

  @Override
  public void updateInputs(MagazineIOInputs inputs) {
    // Update the inputs with the current state of the magazine
    inputs.isUp = upLimitSwitch.get();
    inputs.isDown = downLimitSwitch.get();
    inputs.magazineRPM = magazineMotor.get();
  }

  @Override
  public void setMagazineVoltage(double volts) {
    magazineMotor.setVoltage(volts);
  }

  @Override
  public boolean getUpLimitSwitch() {
    return upLimitSwitch.get();
  }

  @Override
  public boolean getDownLimitSwitch() {
    return downLimitSwitch.get();
  }

  @Override
  public void stop() {
    setMagazineVoltage(0.0);
  }
}
