package frc.robot.subsystems.transfer;
// package frc.robot.subsystems.Transfer;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.math.system.LinearSystem;
// import edu.wpi.first.math.system.plant.DCMotor;
// import edu.wpi.first.math.system.plant.LinearSystemId;
// import edu.wpi.first.wpilibj.simulation.DCMotorSim;

// public class TransferIOSim implements TransferIO{

//     //first number is the inertia (in kg*m^2)
//     //secound number is the gear ratio, need to find what they need to be, these are placeholders
//     private final DCMotorSim transferSim;

//     private final DCMotorSim preShootSim;

//     private double transferAppliedVolts = 0.0;
//     private double preShootAppliedVolts = 0.0;

//     public TransferIOSim() {
//         transferSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(
//             TransferConstants.TRANSFER_VELOCITY, TransferConstants.TRANSFER_ACCELERATION),
//             TransferConstants.TRANSFER_GEARBOX, TransferConstants.TRANSFER_INERTIA);

//         preShootSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(
//             TransferConstants.PRESHOOT_VELOCITY, TransferConstants.PRESHOOT_ACCELERATION),
//             TransferConstants.PRESHOOT_GEARBOX, TransferConstants.PRESHOOT_INERTIA);
//     }

//     @Override
//     public void updateInputs(TransferIOInputs inputs){
//         //find out how big dtSeconds(update time) needs to be
//         transferSim.update(0.02);
//         preShootSim.update(0.02);

//         inputs.transferVelocityRPM = transferSim.getAngularVelocityRPM();
//         inputs.preShootVelocityRPM = preShootSim.getAngularVelocityRPM();
//     }

//     @Override
//     public void setTransferVoltage(double volts){
//         //find out what the clamp thing is
//         transferAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
//         transferSim.setInputVoltage(transferAppliedVolts);
//     }

//     @Override
//     public void setPreShootVoltage(double volts){
//         preShootAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
//         transferSim.setInputVoltage(preShootAppliedVolts);
//     }
// }
