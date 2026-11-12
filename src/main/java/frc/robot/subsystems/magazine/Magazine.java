package frc.robot.subsystems.magazine;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Magazine extends SubsystemBase {
  final MagazineIO io;
  private final MagazineIOInputsAutoLogged inputs = new MagazineIOInputsAutoLogged();

  public Magazine(MagazineIO io) {
    this.io = io;
  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
  }

  public MagazineIO getIO() {
    return io;
  }
}
